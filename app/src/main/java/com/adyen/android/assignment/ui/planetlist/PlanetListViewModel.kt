package com.adyen.android.assignment.ui.planetlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.base.BaseViewModel
import com.adyen.android.assignment.data.repo.PlanetaryRepository
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.network.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlanetListViewModel @Inject constructor(private val repository: PlanetaryRepository) :
    BaseViewModel(repository) {

    private val TAG = "PlanetListViewModel"

    private val _weatherDataObserver = MutableLiveData<ResultModel<List<AstronomyResponse>>>()
    val weatherDataObserver: LiveData<ResultModel<List<AstronomyResponse>>> = _weatherDataObserver

    init {
        fetchPicturesData()
    }

    private fun fetchPicturesData() {
        _weatherDataObserver.postValue(ResultModel.Loading(isLoading = true))
        viewModelScope.launch {
            repository.fetchPictures()
                .catch { exception ->
                    Log.i(TAG, "Exception : ${exception.message}")
                    _weatherDataObserver.value =
                        ResultModel.Failure(code = getStatusCode(throwable = exception))
                    _weatherDataObserver.postValue(ResultModel.Loading(isLoading = false))
                } // exception
                .collect { response ->
                    Log.i(TAG, "Response : $response")
                    _weatherDataObserver.postValue(
                        ResultModel.Success(
                            data = response.body() ?: emptyList()
                        )
                    )
                } // collect
        }
    } // fun of fetchTeamMainData

} // class of WeatherViewModel