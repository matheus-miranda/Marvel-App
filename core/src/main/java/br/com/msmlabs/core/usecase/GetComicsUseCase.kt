package br.com.msmlabs.core.usecase

import br.com.msmlabs.core.data.repository.CharactersRepository
import br.com.msmlabs.core.domain.model.Comics
import br.com.msmlabs.core.usecase.GetComicsUseCase.GetComicsParams
import br.com.msmlabs.core.usecase.base.ResultStatus
import br.com.msmlabs.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetComicsUseCase {
    operator fun invoke(params: GetComicsParams): Flow<ResultStatus<List<Comics>>>

    data class GetComicsParams(val characterId: Int)
}

class GetComicsUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository
) : GetComicsUseCase, UseCase<GetComicsParams, List<Comics>>() {

    override suspend fun doWork(params: GetComicsParams): ResultStatus<List<Comics>> {
        val comics = repository.getComics(params.characterId)
        return ResultStatus.Success(comics)
    }
}
