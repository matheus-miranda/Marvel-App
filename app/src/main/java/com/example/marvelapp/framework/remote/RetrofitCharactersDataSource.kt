package com.example.marvelapp.framework.remote

import br.com.msmlabs.core.data.repository.CharactersRemoteDataSource
import br.com.msmlabs.core.domain.model.CharacterPaging
import br.com.msmlabs.core.domain.model.Comics
import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.response.toCharacterModel
import com.example.marvelapp.framework.network.response.toComicModel
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource {

    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }
        return CharacterPaging(data.offset, data.total, characters)
    }

    override suspend fun fetchComics(characterId: Int): List<Comics> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }
}
