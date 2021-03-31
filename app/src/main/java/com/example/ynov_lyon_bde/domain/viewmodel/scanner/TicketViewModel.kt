package com.example.ynov_lyon_bde.domain.viewmodel.scanner

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService
import com.example.ynov_lyon_bde.domain.services.request.CheckTicketRequest

class TicketViewModel : ViewModel() {
        var token:String? = null
        val sharedPreferencesService = SharedPreferencesService()

    suspend fun validationTicket(uid:String,context:Context):Boolean{
        token = sharedPreferencesService.retrived("TOKEN",context)

        var message:String? = null
        val checkTicketRequest = CheckTicketRequest()

        try {
            checkTicketRequest.callTicketRequest(uid,token)
        }
        catch (err: Exception){
            Log.e("message", err.message)
            return false
        }
        return true
    }
}
