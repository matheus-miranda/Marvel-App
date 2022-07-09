package com.example.marvelapp.framework.di

import br.com.msmlabs.core.data.repository.StorageLocalDataSource
import br.com.msmlabs.core.data.repository.StorageRepository
import com.example.marvelapp.framework.StorageRepositoryImpl
import com.example.marvelapp.framework.local.DataStorePreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageRepositoryModule {

    @Binds
    fun bindStorageRepository(repository: StorageRepositoryImpl): StorageRepository

    @Binds
    fun bindLocalDataSource(dataSource: DataStorePreferencesDataSource): StorageLocalDataSource
}