package com.example.pokemonapp.adapter.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemDetailBinding
import com.example.pokemonapp.responses.Type
import com.example.pokemonapp.util.PokemonType

class PokemonTypeAdapter : RecyclerView.Adapter<PokemonTypeAdapter.Holder>() {

    private var typeItems: List<Type> = ArrayList()
    lateinit var onItemClicked: () -> Unit
    lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun updateTypeItems(typeList: List<Type>) {
        typeItems = typeList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(typeItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked, context = parent.context
        )
    }

    override fun getItemCount(): Int {
        return typeItems.size
    }

    class Holder(
        private val binding: ItemDetailBinding,
        private val onItemClicked: () -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        lateinit var content: Type

        fun bind(content: Type) {
            this.content = content

            binding.type.text = content.type.name
            binding.cardV.background.setTint(
                ContextCompat.getColor(
                    context,
                    PokemonType.getTypeColor(content.type.name)
                )
            )

            binding.root.setOnClickListener {
                onItemClicked.invoke()
            }
        }
    }
}