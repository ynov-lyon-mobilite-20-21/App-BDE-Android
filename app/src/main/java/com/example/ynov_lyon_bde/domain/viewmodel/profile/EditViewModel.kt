package com.example.ynov_lyon_bde.domain.viewmodel.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.data.model.DTO.EditUserDTO
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import java.lang.Exception

class EditViewModel : ViewModel() {
    suspend fun edit(firstName: String, lastName:String, mail:String, password:String, promotion: String, formation:String, context : Context):String? {
        var message : String? = null
        val editUserDto = EditUserDTO(
            firstName = firstName,
            lastName = lastName,
            mail = mail,
            password = password,
            promotion = promotion,
            formation = formation
        )
        val authenticationRequests = AuthenticationRequests()
        try {
            authenticationRequests.callEditUserRequest(editUserDto, context)
        }catch(err : Exception){
            Log.e("message", err.message)
            message = gestionMessageErr(err.message)
        }
        return message
    }

    private fun gestionMessageErr(message: String?): String? {
        var messageForUser : String? = null
        when(message){
            "" -> messageForUser = "Erreur"
            "NO_USER" -> messageForUser = "Email ou mot de passe incorrect"
            "USER_INACTIVE" -> messageForUser = "Veuillez valider votre adresse email"
            "INVALID_TOKEN" -> messageForUser = "Compte non valide"
            "USER_DOESNT_EXIST" -> messageForUser = "Email ou mot de passe incorrect"
            "BAD_CREDENTIALS" -> messageForUser = "Formulaire mal renseigné"
        }
        return messageForUser ?: message
    }
}
