package com.example.ynov_lyon_bde.ui.screens.onBoarding.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.onBoarding.ViewPagerViewModel
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        val viewModel = ViewPagerViewModel()

        view.finish.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            viewModel.onBoardingIsFinished(requireActivity())
        }
        return view
    }
}
