package com.adyen.android.assignment.ui.planetdetails

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.base.BaseActivity
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ActivityPlanetDetailsBinding
import com.adyen.android.assignment.network.ResultModel
import com.adyen.android.assignment.ui.planetlist.PlanetListViewModel
import com.adyen.android.assignment.ui.planetlist.PlanetsAdapter
import kotlinx.coroutines.launch

class PlanetDetailsActivity : BaseActivity<ActivityPlanetDetailsBinding>() {

    private val TAG = "PlanetListActivity"

    private val viewModel: PlanetListViewModel by viewModels()

    private lateinit var planetAdapter: PlanetsAdapter

    override fun getViewBinding() = ActivityPlanetDetailsBinding.inflate(layoutInflater)

    override fun setupView() {
        setupViewModelObservers()
        initListeners()
        initPlanetList()
    } // fun of setupView


    private fun initListeners() {

    } // fun of initListeners

    private fun initPlanetList() {
        planetAdapter = PlanetsAdapter(context = this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = planetAdapter
    }// fun of initPlanetList

    override fun setupViewModelObservers() {
        viewModel.weatherDataObserver.observe(this, weatherDataObserver)
    } // fun of setupViewModelObservers


    private fun handleProgress(isLoading: Boolean) {
        if (isLoading)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    } // fun of handleProgress

    private fun onFail() {
        handleProgress(isLoading = false)
    } // fun of onFail*/


    private val weatherDataObserver = Observer<ResultModel<List<AstronomyResponse>>> { result ->
        lifecycleScope.launch {
            when (result) {
                is ResultModel.Loading -> {
                    handleProgress(isLoading = result.isLoading ?: false)
                } // Loading
                is ResultModel.Success -> {
                    onSuccess(data = result.data)
                } // Success
                is ResultModel.Failure -> {
                    onFail()
                } // Fail
            }
        }
    } // weatherDataObserver

    private fun onSuccess(data: List<AstronomyResponse>?) {
        handleProgress(isLoading = false)
        planetAdapter.submitList( data ?: arrayListOf())
    } // fun of onSuccess
}
