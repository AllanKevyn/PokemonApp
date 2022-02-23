package com.example.pokemonapp.adapter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemHomeBinding
import com.example.pokemonapp.responses.PokemonListEntry
import com.squareup.picasso.Picasso

class PokemonAdapter() : RecyclerView.Adapter<PokemonAdapter.Holder>() {

    private var pokemonItems: MutableList<PokemonListEntry> = ArrayList()
    private var t: MutableList<PokemonListEntry> = ArrayList()
    lateinit var onItemClicked: (PokemonListEntry) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsHome(pokemonList: List<PokemonListEntry>) {
        pokemonItems.addAll(pokemonList)
        notifyDataSetChanged()
    }

    fun updateItemsBySearch(pokemonList: List<PokemonListEntry>) {
        pokemonItems = pokemonList.toMutableList()
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

    fun clearList() {
        pokemonItems.clear()
    }

    class Holder(
        private val binding: ItemHomeBinding,
        private val onItemClicked: (PokemonListEntry) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: PokemonListEntry

        fun bind(content: PokemonListEntry) {
            this.content = content

            Picasso.get().load(content.imageUrl).into(binding.pkmImage)
            binding.pkNumber.text = content.number.toString()
            binding.pkmName.text = content.pokemonName
            binding.root.setOnClickListener {
                onItemClicked.invoke(content)
            }
        }
    }
}