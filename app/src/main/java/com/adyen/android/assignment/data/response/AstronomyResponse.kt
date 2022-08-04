package com.adyen.android.assignment.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AstronomyResponse(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
) : Parcelable