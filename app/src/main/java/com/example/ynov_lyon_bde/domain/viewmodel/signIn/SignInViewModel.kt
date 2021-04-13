package com.example.ynov_lyon_bde.domain.viewmodel.signIn

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.data.model.DTO.LoginDTO
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import com.google.android.material.textfield.TextInputLayout

class SignInViewModel : ViewModel() {

    suspend fun login(mail: String, password: String, context : Context) : String? {
        var message : String? = null
        val loginDto = LoginDTO(
            mail = mail,
            password = password
        )
        val authenticationRequests = AuthenticationRequests()
        try{
            if (authenticationRequests.callLoginRequest(loginDto, context)) {
                authenticationRequests.meAndRefreshToken(context)
            }
        }
        catch (err: Exception) {
            Log.e("login request", err.toString())
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

    fun verifyErrorTextInputLayout(editTextContent: String?, til: TextInputLayout,
                                   errMessage: String, focus: Boolean): Boolean
    {
        return if(editTextContent.isNullOrEmpty()){
            if(focus){
                til.requestFocus()
            }
            til.error = errMessage
            true
        }else{
            false
        }
    }

    fun removeErrAfterTextChanged(editText: EditText, textInputLayout:TextInputLayout){
        editText.doAfterTextChanged {
            textInputLayout.error = null
        }
    }
}
