package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.msmlabs.core.domain.model.Character
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCharacterBinding
import com.example.marvelapp.presentation.util.OnCharacterItemClick

class CharactersViewHolder(
    itemCharacterBinding: ItemCharacterBinding,
    private val onItemClick: OnCharacterItemClick
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val textName = itemCharacterBinding.tvName
    private val imageCharacter = itemCharacterBinding.ivCharacter

    fun bind(character: Character) {
        textName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)

        itemView.setOnClickListener {
            onItemClick(character, imageCharacter)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: OnCharacterItemClick
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(itemBinding, onItemClick)
        }
    }
}