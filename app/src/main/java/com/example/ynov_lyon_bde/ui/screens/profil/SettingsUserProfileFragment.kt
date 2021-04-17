package com.example.ynov_lyon_bde.ui.screens.profil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import kotlinx.android.synthetic.main.fragment_settings_user_profile.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SettingsUserProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_user_profile, container, false)
        // Redirect to last view
        view.back_icon.setOnClickListener{
            findNavController().popBackStack()
        }
        //Redirect to personal informations user fragment
        view.personalInformationButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.actionSettingsUserProfileToPersonalInformationsUser)
        }

        val authenticationRequests = AuthenticationRequests()
        // Logout
        view.logoutButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                var message: Boolean? = null
                val deferred = async(Dispatchers.IO) {
                    //call requests
                    message = context?.let { it1 -> authenticationRequests.callLogoutRequest(it1) }
                }
                deferred.await()
                if (message.toString() === "true") {
                    // Toast success message
                    Toast.makeText(context, "Déconnexion", Toast.LENGTH_SHORT).show()
                    // Redirection to event fragment
                    Navigation.findNavController(view)
                        .navigate(R.id.actionSettingsUserProfileToEventsFragment)
                } else {
                    // Toast error message
                    Toast.makeText(context, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // Delete account
        view.deleteAccountButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                var message: Boolean? = null
                val deferred = async(Dispatchers.IO) {
                    //call requests
                    message = context?.let { it1 -> authenticationRequests.callDeleteUserRequest(it1) }
                }
                deferred.await()
                if (message.toString() === "true") {
                    // Toast success message
                    Toast.makeText(context, "Votre compte à été supprimé", Toast.LENGTH_SHORT).show()
                    // Redirection to event fragment
                    Navigation.findNavController(view)
                        .navigate(R.id.actionSettingsUserProfileToEventsFragment)
                } else {
                    // Toast error message
                    Toast.makeText(context, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}
