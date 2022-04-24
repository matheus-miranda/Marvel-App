package com.example.marvelapp.framework.di

import br.com.msmlabs.core.usecase.base.AppCoroutinesDispatchers
import br.com.msmlabs.core.usecase.base.CoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Provides
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}
