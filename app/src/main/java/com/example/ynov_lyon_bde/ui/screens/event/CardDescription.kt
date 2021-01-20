package com.example.ynov_lyon_bde.ui.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_card_description.view.*

class CardDescription : Fragment() {

    val args:CardDescriptionArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_description, container, false)

        view.back_home_fragment.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_card_description_to_homeFragment) }

        val eventObject = args.eventObject

        val name: TextView = view.findViewById(R.id.eventTitle)
        name.setText(eventObject.name)

        val type:TextView = view.findViewById(R.id.eventDescriptionType)
        type.setText(eventObject.type)

        val date:TextView = view.findViewById(R.id.dateEvent)
        date.setText(eventObject.date)

        val description:TextView = view.findViewById(R.id.eventDescription)
        description.setText(eventObject.description)

        val hour:TextView = view.findViewById(R.id.eventHour)
        hour.setText(eventObject.hour)

        val address:TextView = view.findViewById(R.id.eventAddress)
        address.setText(eventObject.address)
        return view
    }

}
