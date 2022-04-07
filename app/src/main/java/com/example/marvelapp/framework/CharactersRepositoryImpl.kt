package com.example.marvelapp.framework

import androidx.paging.PagingSource
import br.com.msmlabs.core.data.repository.CharactersRemoteDataSource
import br.com.msmlabs.core.data.repository.CharactersRepository
import br.com.msmlabs.core.domain.model.Character
import com.example.marvelapp.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}
