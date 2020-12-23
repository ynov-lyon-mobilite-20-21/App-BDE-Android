package com.example.ynov_lyon_bde.ui.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.data.model.Event
import com.example.ynov_lyon_bde.domain.viewmodel.event.EventViewModel
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val eventViewModel = EventViewModel()

        for (event in eventViewModel.eventList) {
            view.eventLinearLayout.addView(EventCard(event))
        }

        //TODO Il faut chercher sur internet deux choses :
        // Est-ce qu'on peut afficher des éléments dynamique dans une listView, si oui, essayer comme ça
        // Est-ce qu'on peut afficher des éléments dynamique dans un linearLayout qui est lui-même dans une scrollView ?
        // L'objectif est de créer une liste d'éléments graphiques *card* basé sur le nombre d'élément contenu dans eventViewModel.eventList

        //TODO avec cette liste il faut envoyer les infos de chaque Event() de la liste dans les paramètres de la card (Titre, image, eventType)

        //        view.cardview1.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_card_description) }

        return view
    }

}


