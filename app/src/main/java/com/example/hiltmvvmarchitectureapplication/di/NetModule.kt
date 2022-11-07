package com.example.hiltmvvmarchitectureapplication.di

import com.example.hiltmvvmarchitectureapplication.BuildConfig
import com.example.hiltmvvmarchitectureapplication.data.api.Apis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).build()
    }

    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): Apis {
        return retrofit.create(Apis::class.java)
    }

}