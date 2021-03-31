package com.example.ynov_lyon_bde.domain.services.request


import android.util.Log
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService

class CheckTicketRequest(){
    private val bdeApiService = BdeApiService()
    private val errorManager = ErrorManager()

    suspend fun callTicketRequest(uid:String,token:String?):Boolean{
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.TICKET, uid, token)
        if(errorManager.handleException(response, ErrorManager.ErrorType.ERROR)){
            Log.d("response",response)
        }
        return true
    }
}
