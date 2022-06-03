package br.com.msmlabs.core.data.repository

import br.com.msmlabs.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAll(): Flow<List<Character>>
    suspend fun saveFavorite(character: Character)
    suspend fun deleteFavorite(character: Character)
    suspend fun isFavorite(characterId: Int): Boolean
}
