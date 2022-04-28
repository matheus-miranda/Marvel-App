package br.com.msmlabs.core.data.repository

import br.com.msmlabs.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {
    fun fetchAll(): Flow<List<Character>>
    suspend fun save(character: Character)
    suspend fun delete(character: Character)
}