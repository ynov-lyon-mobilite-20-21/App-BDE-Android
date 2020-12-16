package com.example.ynov_lyon_bde.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_card_description.view.*

class CardDescription : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_description, container, false)

        view.button1.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_card_description_to_homeFragment)}

        return view
    }

}
