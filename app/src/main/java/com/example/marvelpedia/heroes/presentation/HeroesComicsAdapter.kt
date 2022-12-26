package com.example.marvelpedia.heroes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelpedia.R
import com.example.marvelpedia.databinding.GridItemBinding
import com.example.marvelpedia.heroes.domain.model.ComicsModel

class HeroesComicsAdapter (var heroesComics: List<ComicsModel>): RecyclerView.Adapter<HeroesComicsAdapter.HeroesComicsViewHolder>() {

    inner class HeroesComicsViewHolder(view:View):RecyclerView.ViewHolder(view){

        private val binding = GridItemBinding.bind(view)

        fun bind(comicsModel: ComicsModel){
            binding.apply {
                this.imagen.load((comicsModel.thumbnail?.path+"."+comicsModel.thumbnail?.extension).replace("http","https"))
                this.nombre.text = comicsModel.title
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesComicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroesComicsViewHolder(layoutInflater.inflate(R.layout.grid_item, parent,false))
    }

    override fun onBindViewHolder(holder: HeroesComicsViewHolder, position: Int) {
        val comic: ComicsModel = heroesComics[position]
        holder.bind(comic)
    }

    override fun getItemCount(): Int = heroesComics.size
}