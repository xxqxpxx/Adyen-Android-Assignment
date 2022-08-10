package com.adyen.android.assignment.ui.planetlanding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.data.response.AstronomyResponse
import com.adyen.android.assignment.databinding.ItemPlanetBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class PlanetsAdapter(
    private var planetList: List<AstronomyResponse>? = arrayListOf(),
    private val context: Context,
    private val selectedInterestsLD: MutableLiveData<AstronomyResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(playerList: List<AstronomyResponse>) {
        this.planetList = playerList
        notifyDataSetChanged()
    } // fun of submitList

    override fun getItemCount(): Int {
        return planetList?.size ?: 0
    } // fun of getItemCount


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderPlanet(ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    } // fun of onCreateViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        planetList?.get(position)?.let {
            (holder as ViewHolderPlanet).bind(context = context, planet = it, selectedInterestsLD)
        }
    } // fun of onBindViewHolder

    private class ViewHolderPlanet(val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            context: Context,
            planet: AstronomyResponse,
            onSelect: MutableLiveData<AstronomyResponse>
        ) {
            binding.txtPlanetName.text = planet.title
            binding.txtPlanetDate.text = planet.date

            if (planet.media_type != "video")
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
            else
                Glide.with(context)
                    .load(AppCompatResources.getDrawable(context, R.drawable.ic_vector))
                    .into(binding.imgPlanet)

            binding.root.setOnClickListener {
                onSelect.value = (planet)
            }
        } // fun of bind
    } // class of ViewHolder
}