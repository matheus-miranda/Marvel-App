package com.example.marvelapp.framework.di

import br.com.msmlabs.core.usecase.GetCharactersUseCaseImpl
import br.com.msmlabs.core.usecase.GetCharactersUseCase
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCaseImpl
import br.com.msmlabs.core.usecase.GetCharacterCategoriesUseCase
import br.com.msmlabs.core.usecase.AddFavoriteUseCaseImpl
import br.com.msmlabs.core.usecase.AddFavoriteUseCase
import br.com.msmlabs.core.usecase.CheckFavoriteUseCase
import br.com.msmlabs.core.usecase.CheckFavoriteUseCaseImpl
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
}
