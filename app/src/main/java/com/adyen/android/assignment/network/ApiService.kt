package com.adyen.android.assignment.network

import com.adyen.android.assignment.data.response.AstronomyResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    /**
     * APOD - Astronomy Picture of the day.
     * See [the docs](https://api.nasa.gov/) and [github micro service](https://github.com/nasa/apod-api#docs-)
     */
/*
    @GET("planetary/apod?count=20&api_key=${BuildConfig.API_KEY}")
    suspend fun getPictures(): Response<List<AstronomyPicture>>
*/

    @GET(Constants.getPictures)
    suspend fun getPictures(): Response<List<AstronomyResponse>>

}