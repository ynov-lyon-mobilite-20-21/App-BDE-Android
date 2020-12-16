package com.example.ynov_lyon_bde.ui.screens.onBoarding.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.onBoarding.ViewPagerViewModel
import kotlinx.android.synthetic.main.fragment_second_screen.view.*

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second_screen, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val viewModel = ViewPagerViewModel()

        view.next2.setOnClickListener{
            if (viewPager != null) {
                viewModel.goToNext(viewPager)
            }
        }
        return view
    }
}
