package com.example.ynov_lyon_bde.ui.screens.profil

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_edit_informations_profile.*
import kotlinx.android.synthetic.main.fragment_edit_informations_profile.view.*
import kotlinx.android.synthetic.main.fragment_personal_informations_user.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.back_icon

class EditInformationsProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_edit_informations_profile, container, false)

        // Redirect to last view
        view.back_icon.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get current user value and assign his values
        editTextLastName.setText("HAMEL-POIRAT")
        editTextFirstName.setText("Maeva")
        editLevel.setText("B2")
        editFormation.setText("Création & Design")

        //Promotion values
        val promotion = arrayOf("B1", "B2", "B3", "M1", "M2")
        //Formation values
        val formation = arrayOf(
            "Animation 3D",
            "Audiovisuel",
            "Création & Design",
            "Marketing Communication",
            "Informatique"
        )
        //Create list
        val adapterPromotion = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, promotion)
        val adapterFormation = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, formation)
        //Add list to edit input
        view.editLevel.setAdapter(adapterPromotion)
        view.editFormation.setAdapter(adapterFormation)

        // Get form values
        validateProfileButton.setOnClickListener {
            val firstname = editTextFirstName.text.toString()
            val lastname = editTextLastName.text.toString()
            val level = editLevel.text.toString()
            val promotion = editFormation.text.toString()
            val lastPassword = editTextLastPassword.text.toString()
            val newPassword = editTextNewPassword.text.toString()
            val confirmNewPassword = editTextConfirmNewPassword.text.toString()
        }
    }
}
