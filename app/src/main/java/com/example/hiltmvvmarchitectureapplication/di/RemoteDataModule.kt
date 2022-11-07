package com.example.hiltmvvmarchitectureapplication.di

import com.example.hiltmvvmarchitectureapplication.BuildConfig
import com.example.hiltmvvmarchitectureapplication.data.api.Apis
import com.example.hiltmvvmarchitectureapplication.mvvm.repository.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {


    @Singleton
    @Provides
    fun provideRemoteDataRepository(api: Apis): MovieRemoteDataSourceImpl {
        return MovieRemoteDataSourceImpl(api,BuildConfig.API_KEY)
    }

}