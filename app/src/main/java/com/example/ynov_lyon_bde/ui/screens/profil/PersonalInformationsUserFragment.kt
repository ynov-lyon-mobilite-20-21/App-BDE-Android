package com.example.ynov_lyon_bde.ui.screens.profil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_personal_informations_user.*
import kotlinx.android.synthetic.main.fragment_personal_informations_user.view.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.back_icon

class PersonalInformationsUserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal_informations_user, container, false)
        // Redirect to last view
        view.back_icon.setOnClickListener {
            findNavController().popBackStack()
        }

        //Redirect to edit informations profile fragment
        view.editProfileButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.actionPersonalInformationsUserToEditInformationsProfileFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get infos current user
        val sharedPreferencesService = SharedPreferencesService()
        val currentUser = context?.let { sharedPreferencesService.retrivedUser(it)}
        val firstname = currentUser?.firstName.toString()
        val lastname = currentUser?.lastName.toString()
        val promotion = currentUser?.promotion.toString()
        val formation = currentUser?.formation.toString()
        val completeName = "$lastname $firstname"
        val completeFormation = "$promotion - $formation"
        // Assign his values
        editTextLastNameFirstName.setText(completeName)
        editTextPromotion.setText(completeFormation)
    }

}
