package com.example.ynov_lyon_bde.ui.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.data.model.Event
import com.example.ynov_lyon_bde.domain.viewmodel.event.EventObject
import com.example.ynov_lyon_bde.domain.viewmodel.event.EventViewModel
import com.example.ynov_lyon_bde.domain.viewmodel.event.RecyclerViewAdapter
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    private val  data = listOf(
    EventObject("","Espit","Soirée Etudiante","204","hgldkbchbqdljhcdbnlkbch<lbdhclb<dhlbchdbkl<cdbkcbdklhbvjk","21:00","5 rue de chez michel"),
    EventObject("","Espit Night","Soirée Etudiante","dsdsq","hgldkbchbqdljcqschcdbnlkbch<lbdhclb<dhlbchdbkl<cdbkcbdklhbvjk","22:00","5 rue de chez michel"),
    EventObject("","Espit de fou","Soirée Etudiante","54645","hgldkbchbqdljhcdbnlkbch<lbdhclb<dhlbchdbkl<cdbkcbdklhbvjk","21:00","5 rue de chez michel"),
    EventObject("","Espit yes","Soirée Etudiante","204504","hgldkbchbqdljhcdbnlkbch<lbdhclb<dhlbchdbkl<cdbkcbdklhbvjk","21:00","5 rue de chez michel")
    )

    //Populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //RecyclerView initialized here
        recyclerViewCard.apply {
            //Set a LinearLayoutManager to handle Android; Correctly positions all the data in the list.
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            //Set the custom adapter to the RecyclerView; Links the RecyclerView view to a list of data.
            adapter = RecyclerViewAdapter(data)
        }
    }
}
