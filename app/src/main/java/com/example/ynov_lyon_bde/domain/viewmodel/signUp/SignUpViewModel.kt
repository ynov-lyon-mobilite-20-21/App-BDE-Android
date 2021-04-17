package com.example.ynov_lyon_bde.domain.viewmodel.signUp

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.data.model.DTO.LoginDTO
import com.example.ynov_lyon_bde.data.model.DTO.UserDTO
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_createuser.*
import java.lang.Exception

class SignUpViewModel : ViewModel() {

    suspend fun create(lastName: String, firstName:String, mail: String, password: String, promotion: String, formation: String, context : Context):String? {
        var message : String? = null
        val userDto = UserDTO(
            firstName = firstName,
            lastName = lastName,
            mail = mail,
            password = password,
            promotion = promotion,
            formation = formation
        )
        val loginDto = LoginDTO(
            mail = mail,
            password = password
        )
        val authenticationRequests = AuthenticationRequests()
        try{
            authenticationRequests.callRegisterRequest(userDto)
            /*
            if (authenticationRequests.callRegisterRequest(userDto)) {
                if (authenticationRequests.callLoginRequest(loginDto, context)) {
                    authenticationRequests.meAndRefreshToken(context)
                }
            }

             */
        }catch(err : Exception){
            Log.e("create request", err.toString())
            message = messageErrorForUser(err.message)
        }
        return message
    }

    private fun messageErrorForUser(message: String?): String? {
        var messageForUser : String? = null
        when(message){
            "UNKNOWN_ERROR" -> messageForUser = "Erreur inconnue"
            "MALFORMED_JSON" -> messageForUser = "Champs inattendus pour la création de compte"
            "USER_INACTIVE" -> messageForUser = "Ce compte existe déjà mais n'est pas actif"
            "USER_DOESNT_EXIST" -> messageForUser = "Email ou mot de passe incorrect"
            "BAD_CREDENTIALS" -> messageForUser = "Formulaire mal renseigné"
            "EMAIL_REQUIRED" -> messageForUser = "Email non renseigné"
            "PASSWORD_REQUIRED" -> messageForUser = "Mot de passe non renseigné"
            "FIRSTNAME_REQUIRED" -> messageForUser = "Prénom non renseigné"
            "LASTNAME_REQUIRED" -> messageForUser = "Nom non renseigné"
            "PROMOTION_REQUIRED" -> messageForUser = "Promotion non renseignée"
            "FORMATION_REQUIRED" -> messageForUser = "Formation non renseignée"
            "USER_ALREADY_EXISTS" -> messageForUser = "Ce compte existe déjà"
        }
        return messageForUser
    }

    fun emptyErrorTextInputLayout(editTextContent: String?, til: TextInputLayout,
                                  errMessage: String, focus: Boolean): Boolean
    {
        return if(editTextContent.isNullOrEmpty()){
            if(focus){
                til.requestFocus()
            }
            til.isErrorEnabled
            til.error = errMessage
            true
        }else{
            false
        }
    }

    fun setErrorTextInputLayout(til: TextInputLayout,
                                errMessage: String, focus: Boolean){
        if(focus){
            til.requestFocus()
        }
        til.boxStrokeColor = Color.parseColor("#b00020")
        til.error = errMessage
    }

    fun removeErrAfterTextChanged(editText: EditText, til: TextInputLayout){
        editText.doAfterTextChanged {
            til.error = null
            til.boxStrokeColor = Color.parseColor("#23B2A4")
        }
    }

}
