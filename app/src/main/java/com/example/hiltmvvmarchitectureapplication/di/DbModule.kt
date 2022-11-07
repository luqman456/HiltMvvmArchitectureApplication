package com.example.hiltmvvmarchitectureapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hiltmvvmarchitectureapplication.data.db.ArtistDao
import com.example.hiltmvvmarchitectureapplication.data.db.MovieDao
import com.example.hiltmvvmarchitectureapplication.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "MovieDatabase").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(movieDatabase: MovieDatabase): ArtistDao {
        return movieDatabase.artistDao()
    }
}