package com.example.marvelapp.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.msmlabs.core.usecase.AddFavoriteUseCase
import br.com.msmlabs.core.usecase.CheckFavoriteUseCase
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCase
import br.com.msmlabs.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase,
    checkFavoriteUseCase: CheckFavoriteUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val categories = UiActionStateLiveData(coroutinesDispatchers.main(), getCharacterCategoriesUseCase)
    val favorite = FavoriteUiActionStateLiveData(coroutinesDispatchers.main(), checkFavoriteUseCase, addFavoriteUseCase)
}
