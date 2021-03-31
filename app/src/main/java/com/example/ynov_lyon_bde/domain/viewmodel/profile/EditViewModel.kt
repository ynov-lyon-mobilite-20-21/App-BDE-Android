package com.example.ynov_lyon_bde.domain.viewmodel.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.data.model.DTO.EditUserDTO
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import java.lang.Exception

class EditViewModel : ViewModel() {
    suspend fun edit(firstName: String, lastName:String, level: String, promotion: String, lastPassword: String, newPassword: String, confirmNewPassword: String, context : Context):String? {
        var message : String? = null
        val editUserDto = EditUserDTO(
            firstName = firstName,
            lastName = lastName,
            level = level,
            promotion = promotion,
            lastPassword = lastPassword,
            newPassword = newPassword,
            confirmNewPassword = confirmNewPassword
        )
        val authenticationRequests = AuthenticationRequests()
        try {
            authenticationRequests.callEditUserRequest(editUserDto, context)
        }catch(err : Exception){
            Log.e("message", err.message)
//            message = gestionMessageErr(err.message)
        }
        return message
    }
}
