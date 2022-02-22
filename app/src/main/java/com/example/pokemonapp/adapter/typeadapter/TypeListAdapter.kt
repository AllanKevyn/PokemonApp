package com.example.pokemonapp.adapter.typeadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemHomeBinding
import com.example.pokemonapp.responses.typelist.Pokemon

class TypeListAdapter : RecyclerView.Adapter<TypeListAdapter.Holder>() {

    private var pokemonItems: List<Pokemon> = ArrayList()
    lateinit var onItemClicked: () -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsHome(pokemonList: List<Pokemon>) {
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

        lateinit var content: Pokemon

        fun bind(content: Pokemon) {
            this.content = content


            binding.pkmName.text = content.pokemon.name
            binding.root.setOnClickListener {
                onItemClicked.invoke()
            }
        }
    }
}