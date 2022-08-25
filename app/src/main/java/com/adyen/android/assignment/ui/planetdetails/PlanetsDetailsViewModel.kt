package com.adyen.android.assignment.ui.planetdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.base.BaseViewModel
import com.adyen.android.assignment.data.helper.ComplexPreferencesImpl
import com.adyen.android.assignment.data.repo.LocalFavouritePlanetsRepository
import com.adyen.android.assignment.data.repo.PlanetaryRepository
import com.adyen.android.assignment.data.response.AstronomyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsDetailsViewModel @Inject constructor() : BaseViewModel() {
    private val TAG = "PlanetListViewModel"

    private val _planetsFavDataObserver = MutableLiveData<Boolean>()
   val planetsDataObserverFav: LiveData<Boolean> = _planetsFavDataObserver

    lateinit var planet : AstronomyResponse
    var isFavPlanet : Boolean = false

    private lateinit var localFavouritePlanetsRepository : LocalFavouritePlanetsRepository

    fun setComplexPref(complexPreferences: ComplexPreferencesImpl) {
        localFavouritePlanetsRepository = LocalFavouritePlanetsRepository(complexPreferences)
    }

    fun handleFavClicked(selected: Boolean) {
        isFavPlanet = selected
        if (selected){
            saveToLocalStorage()
        }else{
            removeFromLocalStorage()
        }
        _planetsFavDataObserver.postValue(isFavPlanet)
    }

    private fun removeFromLocalStorage() {
        viewModelScope.launch {
           localFavouritePlanetsRepository.removeFavouritePlanet(planet)
        }
    }

    private fun saveToLocalStorage() {
        viewModelScope.launch {
           localFavouritePlanetsRepository.saveFavouritePlanet(planet)
        }
    }

    fun setPlanetData(astronomyResponse: AstronomyResponse) {
        planet =  astronomyResponse
    }

    fun handleIfAlreadyFavourite() {
        viewModelScope.launch {

            val list = localFavouritePlanetsRepository.getFavouritePlanetList()
            if (list.contains(planet)) {
                updateFaviconandstatus()
            }

        }
    }

    private fun updateFaviconandstatus(){
        isFavPlanet = true
       _planetsFavDataObserver.postValue(true)
    }
}

