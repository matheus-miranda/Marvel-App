package com.example.marvelapp.framework.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.msmlabs.core.data.repository.CharactersRemoteDataSource
import com.example.marvelapp.framework.db.AppDatabase
import com.example.marvelapp.framework.db.entity.CharacterEntity
import com.example.marvelapp.framework.db.entity.RemoteKey
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val query: String,
    private val orderBy: String,
    private val database: AppDatabase,
    private val remoteDataSource: CharactersRemoteDataSource
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = database.characterDao()
    private val remoteKeyDao = database.remoteKeyDao()

    @Suppress("ReturnCount")
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharacterEntity>): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }
                    if (remoteKey.nextOffset == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextOffset
                }
            }

            val queries = hashMapOf("offset" to offset.toString())

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            if (orderBy.isNotEmpty()) {
                queries["orderBy"] = orderBy
            }

            val charactersPaging = remoteDataSource.fetchCharacters(queries)
            val responseOffset = charactersPaging.offset
            val totalCharacters = charactersPaging.total

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearAll()
                    characterDao.clearAll()
                }
                remoteKeyDao.insertOrReplace(
                    RemoteKey(nextOffset = responseOffset + state.config.pageSize)
                )

                val characterEntities = charactersPaging.characters.map {
                    CharacterEntity(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl
                    )
                }

                characterDao.insertAll(characterEntities)
            }

            MediatorResult.Success(endOfPaginationReached = responseOffset >= totalCharacters)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}