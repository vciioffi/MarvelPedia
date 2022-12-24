package com.example.marvelpedia.heroes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelpedia.R
import com.example.marvelpedia.heroes.domain.model.HeroesModel

class HeroesAdapter(
    var hereosList: MutableList<HeroesModel>
) : RecyclerView.Adapter<HeroesAdapter.HeoresViewHolder>() {

    inner class HeoresViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.imgCharacter)
        val title: TextView = itemView.findViewById(R.id.textName)

        fun bind(heroe: HeroesModel){
            title.text =heroe.name
            image.load((heroe.thumbnail?.path+"."+heroe.thumbnail?.extension).replace("http","https"))

        }
    }

    fun addData(newData: List<HeroesModel>) {
        hereosList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeoresViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return HeoresViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeoresViewHolder, position: Int) {
        val heroe = hereosList[position]
        holder.bind(heroe)
    }

    override fun getItemCount(): Int = hereosList.size

}