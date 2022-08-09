package com.adyen.android.assignment.ui.planetlanding

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.base.BaseActivity
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ActivityPlanetsLandingBinding
import com.adyen.android.assignment.network.ResultModel
import com.adyen.android.assignment.ui.planetdetails.PlanetDetailsActivity
import com.adyen.android.assignment.ui.planetdetails.PlanetDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetsLandingActivity : BaseActivity<ActivityPlanetsLandingBinding>() {

    private val TAG = "PlanetsLandingActivity"

    private val viewModel: PlanetDetailsViewModel by viewModels()

    private lateinit var planetAttributesAdapter: PlanetsAdapter

    override fun getViewBinding() = ActivityPlanetsLandingBinding.inflate(layoutInflater)

    override fun setupView() {
        setupViewModelObservers()
        initListeners()
        fillData()
    } // fun of setupView


    private fun fillData(){
        // adapter
        initPlanetList()
    }

    private fun initListeners() {
        // handle Fab button
         binding.extendedFab.setOnClickListener {
             var astronomyResponse = AstronomyResponse("20/20/2022"  , getString(R.string.testtext) ,
                 "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg", "" ,
                 "" , "", "the milky way" , "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg")


             val myIntent = Intent(this, PlanetDetailsActivity::class.java)
             myIntent.putExtra("planetDetails",  astronomyResponse )
             this.startActivity(myIntent)
        }


        // handle error refresh click
        binding.layoutError.button.setOnClickListener {
            viewModel.refresh()
            hideErrorAndRefresh()
        }


    } // fun of initListeners

    private fun hideErrorAndRefresh() {
        handleError(isError = false)
    }

    private fun initPlanetList() {
        planetAttributesAdapter = PlanetsAdapter(context = this@PlanetsLandingActivity)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = planetAttributesAdapter
    } // fun of initPlanetList

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
        handleError(isError = true)
    } // fun of onFail*/

    private fun handleError(isError :Boolean) {
        if (isError) {
            binding.layoutError.layoutError.visibility = View.VISIBLE
            binding.extendedFab.visibility = View.GONE
        }else{
            binding.layoutError.layoutError.visibility = View.GONE
            binding.extendedFab.visibility = View.VISIBLE
        }
    }

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
        planetAttributesAdapter.submitList((data ?: arrayListOf()) as ArrayList<AstronomyResponse>)
    } // fun of onSuccess
}
