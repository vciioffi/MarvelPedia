package com.example.marvelpedia.heroes.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val adapter = ComicsAdapter(arrayListOf()) { comicModel -> onItemClickListener(comicModel) }


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
        binding.recyclerviewComics.layoutManager = GridLayoutManager(activity,2)

        viewLifecycleOwner.lifecycleScope.launch(){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                sharedViewModel.uiState.collect{
                    adapter.addData(it.listComics?.toMutableList() ?: arrayListOf())
                }
            }
        }

        return binding.root
    }

    private fun onItemClickListener(comicsModel: ComicsModel) {
        TODO("not implemented yet")
    }
}