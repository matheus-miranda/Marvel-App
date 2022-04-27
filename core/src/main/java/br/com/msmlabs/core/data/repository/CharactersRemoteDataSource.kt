package br.com.msmlabs.core.data.repository

import br.com.msmlabs.core.domain.model.CharacterPaging
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.domain.model.Event

interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchComics(characterId: Int): List<Comics>

    suspend fun fetchEvents(characterId: Int): List<Event>
}
