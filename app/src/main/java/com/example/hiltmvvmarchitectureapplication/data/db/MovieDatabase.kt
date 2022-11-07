package com.example.hiltmvvmarchitectureapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiltmvvmarchitectureapplication.data.model.artist.Artist
import com.example.hiltmvvmarchitectureapplication.data.model.movie.Movie

@Database(
    entities = [Movie::class, Artist::class], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun artistDao(): ArtistDao

}