package com.example.ynov_lyon_bde.domain.viewmodel.signIn

import android.content.Context
import android.graphics.Color
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
            "USER_INACTIVE" -> messageForUser = "Compte inactif"
            "UNKNOWN_ERROR" -> messageForUser = "Erreur inconnue"
            "UNKNOWN_USER" -> messageForUser = "Email incorrect"
            "BAD_CREDENTIALS" -> messageForUser = "Email ou mot de passe incorrect"
        }
        Log.e("message user", messageForUser)
        return messageForUser ?: message
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

    fun removeErrAfterTextChanged(editText: EditText, til:TextInputLayout){
        editText.doAfterTextChanged {
            til.error = null
            til.boxStrokeColor = Color.parseColor("#23B2A4")
        }
    }
}
