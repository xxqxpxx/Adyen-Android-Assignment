package com.adyen.android.assignment.data.helper

import android.content.Context
import android.content.SharedPreferences
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.network.Constants.PREFERENCE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ComplexPreferencesImpl (context: Context) {
    var sharedPreferences: SharedPreferences
    private val gson: Gson = Gson()
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveArrayList(list: java.util.ArrayList<AstronomyResponse>, key: String) {
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }
    fun getArrayList(key: String): java.util.ArrayList<AstronomyResponse>? {
         val json: String? = sharedPreferences.getString(key, null)
        val type = object : TypeToken<java.util.ArrayList<AstronomyResponse>>() {}.type
        return gson.fromJson(json, type)
    }
}