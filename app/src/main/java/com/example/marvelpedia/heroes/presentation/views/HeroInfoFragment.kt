package com.example.marvelpedia.heroes.presentation.views

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.marvelpedia.R
import com.example.marvelpedia.common.presentation.SharedViewModel
import com.example.marvelpedia.databinding.FragmentHeroInfoBinding
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import com.example.marvelpedia.heroes.presentation.adapters.ComicsAdapter
import com.example.marvelpedia.heroes.presentation.adapters.HeroesComicsAdapter
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeroInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeroInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()
    var adapter =
        ComicsAdapter(arrayListOf()) { comicModel -> onItemClickListener(comicModel) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var heroe: HeroesModel? = sharedViewModel.uiState.value.heroeItem?.copy()

        val binding = FragmentHeroInfoBinding.inflate(inflater)
        binding.rvHeroComics.adapter = adapter
        binding.rvHeroComics.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.imgItem.load(
            (heroe?.thumbnail?.path + "." + heroe?.thumbnail?.extension).replace(
                "http",
                "https"
            )
        )
        binding.textViewName.text = heroe?.name
        if (heroe?.description?.isNotEmpty() == true)
            binding.textViewDescription.text = heroe.description
        else
            binding.textViewDescription.apply {
                this.gravity = Gravity.CENTER
                this.text = "This hero doesn't contain a description"
            }
        viewLifecycleOwner.lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.uiState.collect {
                    adapter.comicsList = (it.listHeroesComics ?: arrayListOf())
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return binding.root
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