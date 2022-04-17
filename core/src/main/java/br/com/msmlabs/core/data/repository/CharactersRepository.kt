package br.com.msmlabs.core.data.repository

import androidx.paging.PagingSource
import br.com.msmlabs.core.domain.model.Character
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.domain.model.Event

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    suspend fun getComics(characterId: Int): List<Comics>
    suspend fun getEvents(characterId: Int): List<Event>
}