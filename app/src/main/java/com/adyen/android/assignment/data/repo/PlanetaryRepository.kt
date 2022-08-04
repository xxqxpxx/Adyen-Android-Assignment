package com.adyen.android.assignment.data.repo

import android.util.Log
import com.adyen.android.assignment.base.BaseRepository
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetaryRepository @Inject constructor(private val apiService: ApiService) :
    BaseRepository(apiService) {

    private val TAG = "Planetary Repository"

    fun fetchPictures(): Flow<retrofit2.Response<List<AstronomyResponse>>> {
        return flow {
            val response = apiService.getPictures()
            Log.i(TAG, "fetchMain response $response")
            emit(response)
        }
    }
}