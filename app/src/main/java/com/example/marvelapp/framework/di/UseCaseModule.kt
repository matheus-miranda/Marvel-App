package com.example.marvelapp.framework.di

import br.com.msmlabs.core.usecase.GetCharactersUseCase
import br.com.msmlabs.core.usecase.GetCharactersUseCaseImpl
import br.com.msmlabs.core.usecase.GetComicsUseCase
import br.com.msmlabs.core.usecase.GetComicsUseCaseImpl
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
    fun bindGetComicsUseCase(useCase: GetComicsUseCaseImpl): GetComicsUseCase
}
