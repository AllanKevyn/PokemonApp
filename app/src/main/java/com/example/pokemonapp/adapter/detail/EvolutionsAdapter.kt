package com.example.pokemonapp.adapter.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemEvolutionBinding
import com.example.pokemonapp.responses.evolution.EvolvesTo

class EvolutionsAdapter : RecyclerView.Adapter<EvolutionsAdapter.Holder>() {

    private var evolutionItems: MutableList<EvolvesTo> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun updateEvolutions(evolutionList: List<EvolvesTo>) {
        evolutionItems.addAll(evolutionList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(evolutionItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemEvolutionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return evolutionItems.size
    }

    class Holder(
        private val binding: ItemEvolutionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: EvolvesTo

        fun bind(content: EvolvesTo) {
            this.content = content

            binding.pkmName.text = content.species.name
            binding.root.setOnClickListener {

            }
        }
    }
}