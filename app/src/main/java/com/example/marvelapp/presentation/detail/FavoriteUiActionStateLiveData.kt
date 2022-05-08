package com.example.marvelapp.presentation.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.msmlabs.core.usecase.AddFavoriteUseCase
import com.example.marvelapp.R
import com.example.marvelapp.presentation.extensions.watchStatus
import kotlin.coroutines.CoroutineContext

class FavoriteUiActionStateLiveData(
    private val coroutinesContext: CoroutineContext,
    private val addFavoriteUseCase: AddFavoriteUseCase
) {
    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutinesContext) {
            when (it) {
                Action.Default -> emit(UiState.Icon(R.drawable.ic_favorite_unchecked))
                is Action.Update -> {
                    it.detailViewArgs.run {
                        addFavoriteUseCase(
                            AddFavoriteUseCase.Params(
                                characterId, name, imageUrl
                            )
                        ).watchStatus(
                            loading = {
                                emit(UiState.Loading)
                            },
                            success = {
                                emit(UiState.Icon(R.drawable.ic_favorite_checked))
                            },
                            error = {
                                emit(UiState.Error(R.string.error_add_favorite))
                            }
                        )
                    }
                }
            }
        }
    }

    fun setDefault() {
        action.value = Action.Default
    }

    fun update(detailViewArg: DetailViewArgs) {
        action.value = Action.Update(detailViewArg)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Icon(@DrawableRes val icon: Int) : UiState()
        data class Error(@StringRes val messageResId: Int) : UiState()
    }

    sealed class Action {
        object Default : Action()
        data class Update(val detailViewArgs: DetailViewArgs) : Action()
    }
}