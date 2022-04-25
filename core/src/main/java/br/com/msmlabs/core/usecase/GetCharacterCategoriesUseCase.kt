package br.com.msmlabs.core.usecase

import br.com.msmlabs.core.data.repository.CharactersRepository
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.domain.model.Event
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCase.GetCategoriesParams
import br.com.msmlabs.core.usecase.base.CoroutinesDispatchers
import br.com.msmlabs.core.usecase.base.ResultStatus
import br.com.msmlabs.core.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCharacterCategoriesUseCase {
    operator fun invoke(params: GetCategoriesParams): Flow<ResultStatus<Pair<List<Comics>, List<Event>>>>

    data class GetCategoriesParams(val characterId: Int)
}

class GetCharacterCategoriesUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository,
    private val dispatchers: CoroutinesDispatchers
) : GetCharacterCategoriesUseCase, UseCase<GetCategoriesParams, Pair<List<Comics>, List<Event>>>() {

    override suspend fun doWork(params: GetCategoriesParams): ResultStatus<Pair<List<Comics>, List<Event>>> {
        return withContext(dispatchers.io()) {
            val comicsDeferred = async { repository.getComics(params.characterId) }
            val eventsDeferred = async { repository.getEvents(params.characterId) }

            val comics = comicsDeferred.await()
            val events = eventsDeferred.await()

            ResultStatus.Success(comics to events)
        }
    }
}
