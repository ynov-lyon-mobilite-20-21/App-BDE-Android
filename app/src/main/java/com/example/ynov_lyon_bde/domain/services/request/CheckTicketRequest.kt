package com.example.ynov_lyon_bde.domain.services.request


import org.json.JSONObject

class CheckTicketRequest(){
    private val bdeApiService = BdeApiService()
    private val errorManager = ErrorManager()

    suspend fun callTicketRequest(uid:String,token:String?):String{
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.TICKET, uid, token)

        if(errorManager.handleException(response, ErrorManager.ErrorType.ERROR)){
            val json = response.split(";")[1]
            val lastName = JSONObject(json).getJSONObject("data").getJSONObject("user").getString("lastName").toLowerCase().capitalize()
            val firstName = JSONObject(json).getJSONObject("data").getJSONObject("user").getString("firstName").toLowerCase().capitalize()
            return "$firstName $lastName"
        }
        return ""
    }
}
