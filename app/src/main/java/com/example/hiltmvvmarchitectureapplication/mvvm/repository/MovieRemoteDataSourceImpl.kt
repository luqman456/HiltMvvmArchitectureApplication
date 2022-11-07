package com.example.hiltmvvmarchitectureapplication.mvvm.repository

import android.util.Log
import com.example.hiltmvvmarchitectureapplication.data.api.Apis
import com.example.hiltmvvmarchitectureapplication.data.model.movie.MovieList
import com.example.hiltmvvmarchitectureapplication.others.Resource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apis: Apis, private val apiKey: String
) {

    suspend fun getListOfAllModems(): Resource<MovieList> {
        return try {
            val response = apis.getPopularMovies(apiKey)
            Log.d("Message ListOfAllModems", response.body().toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

}