package com.adyen.android.assignment.ui.planetlanding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ItemPlanetBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class PlanetsAdapter(
    private var planetList: ArrayList<AstronomyResponse>? = arrayListOf(),
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(playerList: ArrayList<AstronomyResponse>) {
        this.planetList = ArrayList()
        this.planetList = playerList
        notifyDataSetChanged()
    } // fun of submitList

    override fun getItemCount(): Int {
        return planetList?.size ?: 0
    } // fun of getItemCount



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderPlanet(
            ItemPlanetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    } // fun of onCreateViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        planetList?.get(position)?.let {
            (holder as ViewHolderPlanet).bind(context = context, planet = it)
        }
    } // fun of onBindViewHolder

    private class ViewHolderPlanet(val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, planet: AstronomyResponse) {
            binding.txtPlanetName.text = planet.title
            binding.txtPlanetDate.text = planet.date

            Glide.with(context)
                .applyDefaultRequestOptions(
                    RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(
                            DiskCacheStrategy.AUTOMATIC
                        )
                )

                .load(planet.url)
                .into(binding.imgPlanet)
        } // fun of bind
    } // class of ViewHolder
}