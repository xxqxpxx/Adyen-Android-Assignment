package com.adyen.android.assignment.ui.planetlanding

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
class PlanetsLandingViewModel @Inject constructor(private val repository: PlanetaryRepository) :
    BaseViewModel(repository) {

    private val TAG = "PlanetListViewModel"

    private val _planetsDataObserver = MutableLiveData<ResultModel<List<AstronomyResponse>>>()
    val planetsDataObserver: LiveData<ResultModel<List<AstronomyResponse>>> = _planetsDataObserver

    val selectedPlanetsLD = MutableLiveData<AstronomyResponse>()

    var list: List<AstronomyResponse> = arrayListOf()
    var listSortedByTitle: List<AstronomyResponse> = arrayListOf()
    var listSortedByDate: List<AstronomyResponse> = arrayListOf()

     lateinit var selectedOrderText: String
     var selectedOrderIndex: Int = 0

    init {
        fetchPicturesData()
    }

    private fun fetchPicturesData() {
        _planetsDataObserver.postValue(ResultModel.Loading(isLoading = true))
        viewModelScope.launch {
            repository.fetchPictures()
                .catch { exception ->
                    Log.i(TAG, "Exception : ${exception.message}")
                    _planetsDataObserver.value = ResultModel.Failure(code = getStatusCode(throwable = exception))
                    _planetsDataObserver.postValue(ResultModel.Loading(isLoading = false))
                } // exception
                .collect { response ->
                    list = response.body() ?: emptyList()
                    Log.i(TAG, "Response : $response")
                    _planetsDataObserver.postValue(
                        ResultModel.Success(
                            data = response.body() ?: emptyList()
                        )
                    )
                } // collect
        }
    } // fun of fetchTeamMainData

    private suspend fun sortByTitle(){
        if (listSortedByTitle.isNotEmpty()){
            _planetsDataObserver.postValue(ResultModel.Success(data = listSortedByTitle ?: emptyList()))
            return
        }

        repository.sortByTitle(list)
            .catch { exception ->
                Log.i(TAG, "Exception : ${exception.message}")
                _planetsDataObserver.value = ResultModel.Failure(code = getStatusCode(throwable = exception))
                _planetsDataObserver.postValue(ResultModel.Loading(isLoading = false))
            } // exception
            .collect { response ->
           //     list = response ?: emptyList()
                listSortedByTitle = response
                Log.i(TAG, "Response : $response")
                _planetsDataObserver.postValue(ResultModel.Success(data = response ?: emptyList())
                )
            } // collect
    }

    private suspend fun sortByDate(){

        if (listSortedByDate.isNotEmpty()){
            _planetsDataObserver.postValue(ResultModel.Success(data = listSortedByDate ?: emptyList()))
            return
        }

        repository.sortByDate(list)
            .catch { exception ->
                Log.i(TAG, "Exception : ${exception.message}")
                _planetsDataObserver.value = ResultModel.Failure(code = getStatusCode(throwable = exception))
                _planetsDataObserver.postValue(ResultModel.Loading(isLoading = false))
            } // exception
            .collect { response ->
            //    list = response ?: emptyList()
                listSortedByDate = response
                Log.i(TAG, "Response : $response")
                _planetsDataObserver.postValue(ResultModel.Success(data = response ?: emptyList())
                )
            } // collect
    }

    fun resetSort(){
        _planetsDataObserver.postValue(ResultModel.Success(list))
    }
    fun sortList(index: Int) {
        _planetsDataObserver.postValue(ResultModel.Loading(isLoading = true))
        viewModelScope.launch {
            if (index == 0) // sort by ttitle
                sortByTitle()
            else // sort by date
                sortByDate()
        }
    }

    fun refresh() {
        fetchPicturesData()
    }

}