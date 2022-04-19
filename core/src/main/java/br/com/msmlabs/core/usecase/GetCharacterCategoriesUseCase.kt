package br.com.msmlabs.core.usecase

import br.com.msmlabs.core.data.repository.CharactersRepository
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.domain.model.Event
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCase.GetComicsParams
import br.com.msmlabs.core.usecase.base.AppCoroutinesDispatchers
import br.com.msmlabs.core.usecase.base.ResultStatus
import br.com.msmlabs.core.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCharacterCategoriesUseCase {
    operator fun invoke(params: GetComicsParams): Flow<ResultStatus<Pair<List<Comics>, List<Event>>>>

    data class GetComicsParams(val characterId: Int)
}

class GetCharacterCategoriesUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository,
    private val dispatchers: AppCoroutinesDispatchers
) : GetCharacterCategoriesUseCase, UseCase<GetComicsParams, Pair<List<Comics>, List<Event>>>() {

    override suspend fun doWork(params: GetComicsParams): ResultStatus<Pair<List<Comics>, List<Event>>> {
        return withContext(dispatchers.io) {
            val comicsDeferred = async { repository.getComics(params.characterId) }
            val eventsDeferred = async { repository.getEvents(params.characterId) }

            val comics = comicsDeferred.await()
            val events = eventsDeferred.await()

            ResultStatus.Success(comics to events)
        }
    }
}
