package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemHomeBinding
import com.example.pokemonapp.responses.PokemonListEntry
import com.squareup.picasso.Picasso

class PokemonAdapter() : RecyclerView.Adapter<PokemonAdapter.Holder>() {

    private var pokemonItems: List<PokemonListEntry> = ArrayList()
    lateinit var onItemClicked: () -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsHome(pokemonList: List<PokemonListEntry>) {
        pokemonItems = pokemonList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(pokemonItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked
        )
    }

    override fun getItemCount(): Int {
        return pokemonItems.size
    }

    class Holder(
        private val binding: ItemHomeBinding,
        private val onItemClicked: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: PokemonListEntry

        fun bind(content: PokemonListEntry) {
            this.content = content

            Picasso.get().load(content.imageUrl).into(binding.pkmImage)
            binding.pkmName.text = content.pokemonName

            binding.root.setOnClickListener {
                onItemClicked.invoke()
            }
        }
    }
}