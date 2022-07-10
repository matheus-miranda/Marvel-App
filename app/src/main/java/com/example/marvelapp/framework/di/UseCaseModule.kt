package com.example.marvelapp.framework.di

import br.com.msmlabs.core.usecase.GetCharactersUseCaseImpl
import br.com.msmlabs.core.usecase.GetCharactersUseCase
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCaseImpl
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCase
import br.com.msmlabs.core.usecase.AddFavoriteUseCaseImpl
import br.com.msmlabs.core.usecase.AddFavoriteUseCase
import br.com.msmlabs.core.usecase.CheckFavoriteUseCase
import br.com.msmlabs.core.usecase.CheckFavoriteUseCaseImpl
import br.com.msmlabs.core.usecase.GetCharactersSortingUseCase
import br.com.msmlabs.core.usecase.GetCharactersSortingUseCaseImpl
import br.com.msmlabs.core.usecase.GetFavoritesUseCase
import br.com.msmlabs.core.usecase.GetFavoritesUseCaseImpl
import br.com.msmlabs.core.usecase.RemoveFavoriteUseCase
import br.com.msmlabs.core.usecase.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetComicsUseCase(useCase: GetCharacterCategoriesUseCaseImpl): GetCharacterCategoriesUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase

    @Binds
    fun bindGetFavoritesUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    @Binds
    fun bindGetCharactersSortingUseCase(useCase: GetCharactersSortingUseCaseImpl): GetCharactersSortingUseCase
}
