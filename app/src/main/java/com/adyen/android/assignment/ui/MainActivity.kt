package com.adyen.android.assignment.ui

import android.annotation.SuppressLint
import com.adyen.android.assignment.base.BaseActivity
import com.adyen.android.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val TAG = "MainActivity"

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupView() {
        initListeners()
        initForeCastList()
    } // fun of setupView


    @SuppressLint("SetTextI18n")
    private fun initListeners() {
    } // fun of initListeners

    private fun initForeCastList() {}// fun of initForeCastList

    override fun setupViewModelObservers() {
    } // fun of setupViewModelObservers


    private fun handleProgress(isLoading: Boolean) {
        //   if(isLoading)
        //   binding.progressBar.visibility = View.VISIBLE
        //   else
        //   binding.progressBar.visibility = View.GONE

    } // fun of handleProgress

    private fun onFail() {
        handleProgress(isLoading = false)
    } // fun of onFail*/

} // class of MainActivity