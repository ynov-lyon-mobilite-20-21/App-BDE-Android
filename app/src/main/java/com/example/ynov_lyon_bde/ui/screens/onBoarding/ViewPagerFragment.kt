package com.example.ynov_lyon_bde.ui.screens.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.ViewPagerAdapter
import com.example.ynov_lyon_bde.ui.screens.onBoarding.viewPager.FirstScreen
import com.example.ynov_lyon_bde.ui.screens.onBoarding.viewPager.SecondScreen
import com.example.ynov_lyon_bde.ui.screens.onBoarding.viewPager.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        view.viewPager.adapter = adapter
        return view
    }
}
