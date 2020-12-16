package com.example.ynov_lyon_bde.ui.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.cardview1.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }
        view.cardview2.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }
        view.cardview3.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }

        return view
    }
}


