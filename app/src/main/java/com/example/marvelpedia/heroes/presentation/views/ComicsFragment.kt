package com.example.marvelpedia.heroes.presentation.views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.marvelpedia.R
import com.example.marvelpedia.common.presentation.SharedViewModel
import com.example.marvelpedia.databinding.FragmentComicsBinding
import com.example.marvelpedia.databinding.FragmentHeroesBinding
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.presentation.adapters.ComicsAdapter
import com.example.marvelpedia.heroes.presentation.adapters.HeroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class ComicsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val adapter =
        ComicsAdapter(arrayListOf()) { comicModel -> onItemClickListener(comicModel) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentComicsBinding.inflate(inflater)
        binding.recyclerviewComics.adapter = adapter
        binding.recyclerviewComics.layoutManager = GridLayoutManager(activity, 2)

        viewLifecycleOwner.lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.uiState.collect {
                    adapter.addData(it.listComics?.toMutableList() ?: arrayListOf())
                }
            }
        }

        binding.searchViewComics.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return searchByName(query)
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })

        return binding.root
    }


    private fun searchByName(name: String?): Boolean {
        if (name != null) {
            adapter.comicsList.clear()
            sharedViewModel.getComicsListByName(name)
            println("fdeasa "+ sharedViewModel.uiState.value.listComics)
        } else {
            adapter.comicsList.clear()
            sharedViewModel.getComicsList()
        }
        return true
    }

    private fun onItemClickListener(comicsModel: ComicsModel) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.setContentView(R.layout.custom_dialog)
        val imageView = dialog?.findViewById<ImageView>(R.id.img)
        val title = dialog?.findViewById<TextView>(R.id.txt_Image_name)
        val btn = dialog?.findViewById<Button>(R.id.btn_close)
        imageView?.load(
            (comicsModel.thumbnail?.path + "." + comicsModel.thumbnail?.extension).replace(
                "http",
                "https"
            )
        )

        title?.text = (comicsModel.title)
        btn?.setOnClickListener {
            dialog.hide()
        }
        dialog?.show()
    }

}