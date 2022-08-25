package com.adyen.android.assignment.data.repo

import com.adyen.android.assignment.data.helper.ComplexPreferencesImpl
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.network.Constants.PLANETS_LIST_PREF_NAME
import com.google.gson.reflect.TypeToken

class LocalFavouritePlanetsRepository(private val complexPreferences: ComplexPreferencesImpl) {
    private val TAG = "LocalFavouritePlanetsRepository"

    fun saveFavouritePlanet(planet : AstronomyResponse) {
        val list = getFavouritePlanetList()
        list.add(planet)
        complexPreferences.saveArrayList( list , PLANETS_LIST_PREF_NAME)
    }

     fun getFavouritePlanetList(): ArrayList<AstronomyResponse> {
         val list = complexPreferences.getArrayList(PLANETS_LIST_PREF_NAME)

         return if (list.isNullOrEmpty()){
             (arrayListOf())
         }else
             list
    }

    fun removeFavouritePlanet(planet: AstronomyResponse) {
        val list = getFavouritePlanetList()
        list.remove(planet)
        complexPreferences.saveArrayList( list , PLANETS_LIST_PREF_NAME)
    }

}