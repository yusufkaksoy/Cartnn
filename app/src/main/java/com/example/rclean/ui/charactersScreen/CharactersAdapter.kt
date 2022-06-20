package com.example.rclean.ui.charactersScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rclean.ui.charactersScreen.CharactersAdapter.*
import com.example.rclean.databinding.CharacterLayoutBinding
import com.example.rclean.network.model.RickMorty

class CharactersAdapter: PagingDataAdapter<RickMorty, MyViewHolder>(diffCallback) {


    inner class MyViewHolder(val binding : CharacterLayoutBinding):
    RecyclerView.ViewHolder(binding.root)
     companion object{
         val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
             override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                 return oldItem.id == newItem.id
             }

             override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                 return oldItem == newItem
             }
         }
     }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            textViewCharacterName.text = "${currentItem?.name}"
            textViewCharacterDescription.text="${currentItem?.species}"
            textViewCharacterLastLocation.text="${currentItem?.location?.name}"
            val imageLink = currentItem?.image
            //coroutine image loader (coil)
            imageViewCharactersFragment.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CharacterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}