package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemHomeBinding
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.PokemonListResult

class PokemonAdapter(
    private var pokemonItems: List<PokemonListResult>
) : RecyclerView.Adapter<PokemonAdapter.Holder>() {

    lateinit var onItemClicked: () -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsHome(pokemonList: PokemonList) {
        pokemonItems = pokemonList.results
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

        lateinit var content: PokemonListResult

        fun bind(content: PokemonListResult) {
            this.content = content

            binding.pkmName.text = content.name

            binding.root.setOnClickListener {
                onItemClicked.invoke()
            }
        }
    }
}