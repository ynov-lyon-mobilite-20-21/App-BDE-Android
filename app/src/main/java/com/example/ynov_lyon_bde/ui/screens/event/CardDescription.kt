package com.example.ynov_lyon_bde.ui.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_card_description.*
import kotlinx.android.synthetic.main.fragment_card_description.view.*

class CardDescription : Fragment() {

    private val args:CardDescriptionArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_description, container, false)

        view.back_home_fragment.setOnClickListener { findNavController().popBackStack() }

        val eventObject = args.eventObject

        view.eventTitle.text = eventObject.name
        view.eventDescriptionType.text = eventObject.type
        view.dateEvent.text = eventObject.date
        view.eventDescription.text = eventObject.description
        view.eventHour.text = eventObject.hour
        view.eventAddress.text = eventObject.address
        return view
    }

}
