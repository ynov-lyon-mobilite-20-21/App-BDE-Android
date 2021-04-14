package com.example.ynov_lyon_bde.ui.screens.profil

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.profile.EditViewModel
//import com.example.ynov_lyon_bde.domain.viewmodel.profile.EditViewModel
import kotlinx.android.synthetic.main.fragment_edit_informations_profile.*
import kotlinx.android.synthetic.main.fragment_edit_informations_profile.view.*
import kotlinx.android.synthetic.main.fragment_personal_informations_user.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.*
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.back_icon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

        val editViewModel = EditViewModel()

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


            Log.d("Valeur : ", lastPassword)
            Log.d("Valeur : ", newPassword)
            Log.d("Valeur : ", confirmNewPassword)

            // Check if all fields are not empty
            if (firstname.isNotEmpty() && lastname.isNotEmpty() && level.isNotEmpty() && promotion.isNotEmpty() && lastPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmNewPassword.isNotEmpty()) {
                // Check if new password and confirm password are equal
//                if (confirmNewPassword === newPassword) {
//                    Log.d("MessageTest", "OK2")

                    // Check if new password and confirm new passwor are different to last password
//                    if ((lastPassword !== newPassword) || (lastPassword !== confirmNewPassword)) {
                        Log.d("MessageTest", "OK3")

                        var message: String? = null
                        GlobalScope.launch(Dispatchers.Main) {
                            val deferred = async(Dispatchers.IO) {
                                //call requests
                                message = context?.let { it1 -> editViewModel.edit(firstname, lastname, "luca.sardellitti@ynov.com", confirmNewPassword, promotion, level, it1) }
                                Log.d("Redirection", message.toString())
                                Toast.makeText(context, "Mise à jour du profile", Toast.LENGTH_SHORT).show()
                            }
                            deferred.await()
                            if (message.isNullOrEmpty()) {
                                Log.d("Redirection", "OK")
                                Navigation.findNavController(view)
                                    .navigate(R.id.actionEditInformationsProfileFragmentToAccountFragment)
                            } else {
                                Log.d("Redirection", "KO")
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }
//                    }
//                    else {
//                        Log.d("MessageTest", "KO")
//                        Toast.makeText(context, "Le nouveau mot de passe ne peut pas être identique à l'ancien", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                else {
//                    Log.d("MessageTest", "KO2")
//                    Toast.makeText(context, "Le nouveau mot de passe et la confirmation ne sont pas identique", Toast.LENGTH_SHORT).show()
//                }
            } else {
                Log.d("MessageTest", "Formulaire mal renseigné (champs vides)")
                Toast.makeText(context, "Formulaire mal renseigné", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
