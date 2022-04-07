package br.com.msmlabs.core.data.repository

import br.com.msmlabs.core.domain.model.CharacterPaging

interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging
}
