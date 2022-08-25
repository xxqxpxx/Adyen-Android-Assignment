package com.adyen.android.assignment.data.response

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AstronomyResponse(
    val copyright: String?,
    val date: String,
    val explanation: String,
    var hdurl: String?,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
) : Parcelable

