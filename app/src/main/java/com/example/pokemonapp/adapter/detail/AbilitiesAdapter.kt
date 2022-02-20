package com.example.pokemonapp.adapter.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemAbilitiesBinding
import com.example.pokemonapp.responses.Ability
import com.example.pokemonapp.responses.AbilityX
import com.example.pokemonapp.util.PokemonType

class AbilitiesAdapter : RecyclerView.Adapter<AbilitiesAdapter.Holder>() {

    private var abilitiesItems: List<Ability> = ArrayList()
    lateinit var onItemClicked: (Ability) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateAbilityItems(abilitiesList: List<Ability>) {
        abilitiesItems = abilitiesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(abilitiesItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemAbilitiesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked,
        )
    }

    override fun getItemCount(): Int {
        return abilitiesItems.size
    }

    class Holder(
        private val binding: ItemAbilitiesBinding,
        private val onItemClicked: (Ability) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: Ability

        fun bind(content: Ability) {
            this.content = content
            //PokemonType.list = content.ability.name
            binding.tvAbility.text = content.ability.name
            binding.root.setOnClickListener {
                onItemClicked.invoke(content)
            }
        }
    }
}