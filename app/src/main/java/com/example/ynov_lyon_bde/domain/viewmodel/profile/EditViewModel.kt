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
            message = gestionMessageErr(err.message)
        }
        return message
    }

    private fun gestionMessageErr(message: String?): String? {
        var messageForUser : String? = null
        when(message){
            "" -> messageForUser = "Erreur"
            "MALFORMED_JSON" -> messageForUser = "Champs inattendus"
            "EMAIL_REQUIRED" -> messageForUser = "Email requis"
            "PASSWORD_REQUIRED" -> messageForUser = "Mot de passe requis"
            "FIRSTNAME_REQUIRED" -> messageForUser = "Prénom requis"
            "LASTNAME_REQUIRED" -> messageForUser = "Nom requis"
            "PROMOTION_REQUIRED" -> messageForUser = "Promotion requis"
            "FORMATION_REQUIRED" -> messageForUser = "Formation requis"
            "UNKNOWN_ERROR" -> messageForUser = "Une erreur est survenu"
            "USER_INACTIVE" -> messageForUser = "L'utilisateur existe déjà mais n'est pas actif"
            "USER_ALREADY_EXISTS" -> messageForUser = "Formulaire mal renseigné"
        }
        return messageForUser ?: message
    }
}


