package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemDetailBinding
import com.example.pokemonapp.responses.PokemonListEntry

class PokemonTypeAdapter () : RecyclerView.Adapter<PokemonTypeAdapter.Holder>() {

    private var typeItems: List<PokemonListEntry> = ArrayList()
    lateinit var onItemClicked: () -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateTypeItems(pokemonList: List<PokemonListEntry>) {
        typeItems = pokemonList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(typeItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked
        )
    }

    override fun getItemCount(): Int {
        return typeItems.size
    }

    class Holder(
        private val binding: ItemDetailBinding,
        private val onItemClicked: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: PokemonListEntry

        fun bind(content: PokemonListEntry) {
            this.content = content

            binding.type.text = ""

            binding.root.setOnClickListener {
                onItemClicked.invoke()
            }
        }
    }
}