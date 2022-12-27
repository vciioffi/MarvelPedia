package com.example.marvelpedia

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelpedia.R
import com.example.marvelpedia.common.presentation.SharedViewModel
import com.example.marvelpedia.databinding.FragmentHeroesBinding
import com.example.marvelpedia.databinding.FragmentWelcomeBinding
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import com.example.marvelpedia.heroes.presentation.HeroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HeroesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()
    val adapter = HeroesAdapter(arrayListOf()){ heroeModel -> onItemClickListener(heroeModel) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHeroesBinding.inflate(inflater)
        binding.recyclerviewHeroes.adapter = adapter
        binding.recyclerviewHeroes.layoutManager = LinearLayoutManager(activity)

        viewLifecycleOwner.lifecycleScope.launch(){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                sharedViewModel.uiState.collect{
                    adapter.addData(it.listHeroes?.toMutableList() ?: arrayListOf())
                }
            }
        }

        binding.recyclerviewHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE
                ) {
                    sharedViewModel.getHeoresList()
                }
            }
        })
        return binding.root
    }
    private fun onItemClickListener(heroesModel: HeroesModel){
        Toast.makeText(activity,heroesModel.name,Toast.LENGTH_SHORT).show()
        sharedViewModel.getHeoresComcicsList(heroesModel.id)
        sharedViewModel.uiState.value.heroeItem = heroesModel
        findNavController().navigate(R.id.action_heroesAndComicsFragment_to_heroInfoFragment)

    }
}