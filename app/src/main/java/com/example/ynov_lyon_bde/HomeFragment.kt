package com.example.ynov_lyon_bde

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.lang.reflect.Array

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.cardview1.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }
        view.cardview2.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }
        view.cardview3.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }

        return view
    }
}


