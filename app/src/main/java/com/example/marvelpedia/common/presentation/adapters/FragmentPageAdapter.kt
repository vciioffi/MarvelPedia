package com.example.marvelpedia.common.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelpedia.ComicsFragment
import com.example.marvelpedia.HeroesFragment
import com.example.marvelpedia.WelcomeFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position ==0)
            HeroesFragment()
        else
            ComicsFragment()
    }

}