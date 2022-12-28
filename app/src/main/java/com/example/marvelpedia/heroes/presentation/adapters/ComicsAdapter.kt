package com.example.marvelpedia.heroes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelpedia.R
import com.example.marvelpedia.databinding.GridItemBinding
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.domain.model.HeroesModel

class ComicsAdapter(
    var comicsList: MutableList<ComicsModel>,
    private val itemClickListener: (ComicsModel) -> (Unit)

) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {


    inner class ComicsViewHolder(
        view: View,
        private val onItemClicked: (comic: ComicsModel) -> Unit

    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = GridItemBinding.bind(view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(comicsModel: ComicsModel) {
            binding.apply {
                this.imagen.load(
                    (comicsModel.thumbnail?.path + "." + comicsModel.thumbnail?.extension).replace(
                        "http",
                        "https"
                    )
                )
                this.nombre.text = comicsModel.title
            }
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            val comic = comicsList[position]
            onItemClicked(comic)
        }
    }

    fun addData(newData: List<ComicsModel>) {
        comicsList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicsViewHolder(layoutInflater.inflate(R.layout.grid_item, parent, false),itemClickListener)

    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comic: ComicsModel = comicsList[position]
        holder.bind(comic)
    }

    override fun getItemCount(): Int = comicsList.size
}