package com.adyen.android.assignment.ui.planetdetails

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.databinding.ItemPlanetAttributesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlanetAttributesAdapter(
    private var planetList: ArrayList<Pair<String, Drawable>> ,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(playerList: ArrayList<Pair<String, Drawable>>) {
        this.planetList = playerList
        notifyDataSetChanged()
    } // fun of submitList

    override fun getItemCount(): Int {
        return planetList.size ?: 0
    } // fun of getItemCount

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderPlanet(ItemPlanetAttributesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    } // fun of onCreateViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        planetList[position].let {
            (holder as ViewHolderPlanet).bind(context = context, planet = it)
        }
    } // fun of onBindViewHolder

    private class ViewHolderPlanet(val binding: ItemPlanetAttributesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, planet: Pair<String, Drawable>) {

            binding.txtPlanetName .text = planet.first

            Glide.with(context)
                .applyDefaultRequestOptions(RequestOptions().fitCenter())
                .load(planet.second)
                .into(binding.imgPlanet)
        } // fun of bind
    } // class of ViewHolder
}