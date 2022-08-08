package com.adyen.android.assignment.ui.planetdetails

import android.graphics.drawable.Drawable
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.base.BaseActivity
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ActivityPlanetDetailsBinding
import com.adyen.android.assignment.network.ResultModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.launch

class PlanetDetailsActivity : BaseActivity<ActivityPlanetDetailsBinding>() {

    private val TAG = "PlanetDetailsActivity"

    private val viewModel: PlanetDetailsViewModel by viewModels()

     private lateinit var planetAttributesAdapter: PlanetAttributesAdapter

    override fun getViewBinding() = ActivityPlanetDetailsBinding.inflate(layoutInflater)

    lateinit var astronomyResponse : AstronomyResponse

    override fun setupView() {
        getData()
        setupViewModelObservers()
        initListeners()
        fillData()
    } // fun of setupView

    private fun getData() {
        val value = intent.extras?.get("planetDetails")
        astronomyResponse = value as AstronomyResponse
    }


    private fun preparePlanetFeatures(): ArrayList<Pair<String, Drawable>> {
        val list = ArrayList<Pair<String , Drawable>>()
        list.add(Pair("It's warped." , resources.getDrawable(R.drawable.ic_warped)))
        list.add(Pair("Over 200 billion stars" , resources.getDrawable(R.drawable.ic_stars)))
        list.add(Pair("It's really dusty and gassy" , resources.getDrawable(R.drawable.ic_dust)))
        list.add(Pair("Black hole at the centre." , resources.getDrawable(R.drawable.ic_blackhole)))
        return list
    }

    private fun fillData(){
        /*
        astronomyResponse = AstronomyResponse("20/20/2022"  , getString(R.string.testtext) ,
            "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg", "" ,
            "" , "the milky way" , "https://apod.nasa.gov/apod/image/0007/ngc1850_hst.jpg")
        */

        binding.txtPlanetTitle.text = astronomyResponse.title
        binding.txtDate.text = astronomyResponse.date
        binding.txtDescription.text = astronomyResponse.explanation

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions().fitCenter())
            .load(astronomyResponse.url)
            .into(binding.imgPlanetBg)

        // adapter
        initPlanetList()
    }

    private fun initListeners() {

    } // fun of initListeners

    private fun initPlanetList() {
        planetAttributesAdapter = PlanetAttributesAdapter(preparePlanetFeatures() , this@PlanetDetailsActivity)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = planetAttributesAdapter
    } // fun of initPlanetList

    override fun setupViewModelObservers() {
       // viewModel.weatherDataObserver.observe(this, weatherDataObserver)
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
     //   planetAttributesAdapter.submitList( data ?: arrayListOf())
    } // fun of onSuccess
}
