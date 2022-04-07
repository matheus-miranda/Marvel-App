package com.example.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.msmlabs.core.data.repository.CharactersRemoteDataSource
import br.com.msmlabs.core.domain.model.Character

class CharactersPagingSource(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val query: String
) : PagingSource<Int, Character>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offset = params.key ?: 0
            val queries = hashMapOf("offset" to offset.toString())

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val charactersPaging = remoteDataSource.fetchCharacters(queries)

            val responseOffset = charactersPaging.offset
            val totalCharacters = charactersPaging.total

            LoadResult.Page(
                data = charactersPaging.characters,
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + LIMIT
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}