package com.example.ynov_lyon_bde.ui.screens.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.fragment_settings_user_profil.view.*

class SettingsUserProfil : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings_user_profil, container, false)

        // Redirect to last view
        view.back_icon.setOnClickListener{
            findNavController().popBackStack()
        }

        // Inflate the layout for this fragment
        return view
    }
}
