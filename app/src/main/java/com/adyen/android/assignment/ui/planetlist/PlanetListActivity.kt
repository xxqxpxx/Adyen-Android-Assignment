package com.adyen.android.assignment.ui.planetlist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.base.BaseActivity
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ActivityPlanetListBinding
import com.adyen.android.assignment.network.ResultModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetListActivity : BaseActivity<ActivityPlanetListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_list)
    }

    private val TAG = "PlanetListActivity"

    private val viewModel: PlanetListViewModel by viewModels()

    private lateinit var planetAdapter: PlanetsAdapter

    override fun getViewBinding() = ActivityPlanetListBinding.inflate(layoutInflater)

    override fun setupView() {
        setupViewModelObservers()
        initListeners()
        initPlanetList()
    } // fun of setupView


    private fun initListeners() {
/*       var astronomyResponse = AstronomyResponse("20/20/2022"  , getString(R.string.testtext) ,
            "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg", "" ,
            "" , "the milky way" , "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg")


        val myIntent = Intent(this, PlanetDetailsActivity::class.java)
        myIntent.putExtra("planetDetails",  astronomyResponse )
        this.startActivity(myIntent)*/

/*        binding.extendedFab.setOnClickListener {

        }*/

    } // fun of initListeners

    private fun initPlanetList() {

        var list :ArrayList<AstronomyResponse> = arrayListOf()

        var astronomyResponse = AstronomyResponse("20/20/2022"  , getString(R.string.testtext) ,
            "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg", "" ,
            "" , "the milky way" , "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg")

        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)
        list.add(astronomyResponse)


        planetAdapter = PlanetsAdapter(list , this)
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
  //      planetAdapter.submitList( data ?: arrayListOf())
    } // fun of onSuccess
}