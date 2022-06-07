package br.com.msmlabs.core.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.msmlabs.core.domain.model.Character
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    fun getCachedCharacters(query: String, pagingConfig: PagingConfig): Flow<PagingData<Character>>
    suspend fun getComics(characterId: Int): List<Comics>
    suspend fun getEvents(characterId: Int): List<Event>
}