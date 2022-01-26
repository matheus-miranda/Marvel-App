package br.com.msmlabs.core.data.repository

import androidx.paging.PagingSource
import br.com.msmlabs.core.domain.model.Character

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
}