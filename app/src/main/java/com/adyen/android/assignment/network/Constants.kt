package com.adyen.android.assignment.network

import com.adyen.android.assignment.BuildConfig

object Constants {

    const val API_TIMEOUT: Long = 60

    const val BASE_URL = BuildConfig.NASA_BASE_URL

    const val getPictures = "planetary/apod?count=25&api_key=${BuildConfig.API_KEY}"

    const val PREFERENCE_NAME = "pref_adyen"

    const val PLANETS_LIST_PREF_NAME = "planets_list"

}