package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class PokemonAdapter(private val pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.pokemon_name)
        val idTextView: TextView = itemView.findViewById(R.id.pokemon_id)
        val imageView: ImageView = itemView.findViewById(R.id.pokemon_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.nameTextView.text = pokemon.name.replaceFirstChar { it.uppercase() }
        holder.idTextView.text = pokemon.id.toString()

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(pokemon.imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.imageView)
    }

    override fun getItemCount() = pokemonList.size
}
