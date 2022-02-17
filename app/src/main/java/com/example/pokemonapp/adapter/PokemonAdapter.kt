package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemHomeBinding

class PokemonAdapter(
    private var itemsSearch: List<String>
) : RecyclerView.Adapter<PokemonAdapter.Holder>() {

    lateinit var onItemClicked: () -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsHome(products: String) {
//        itemsSearch = products.suppliers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemsSearch[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked
        )
    }

    override fun getItemCount(): Int {
        return itemsSearch.size
    }

    class Holder(
        private val binding: ItemHomeBinding,
        private val onItemClicked: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: String

        fun bind(content: String) {
            this.content = content


            onItemClicked.invoke()
        }
    }
}