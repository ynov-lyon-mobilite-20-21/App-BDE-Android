package com.example.ynov_lyon_bde.domain.services.request

import android.content.Context
import android.util.Log
import com.example.ynov_lyon_bde.data.model.DTO.EditUserDTO
import com.example.ynov_lyon_bde.data.model.DTO.LoginDTO
import com.example.ynov_lyon_bde.data.model.DTO.UserDTO
import com.example.ynov_lyon_bde.data.model.User
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService
import org.json.JSONObject
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class AuthenticationRequests() : KoinComponent {

    private val sharedPreferencesService = SharedPreferencesService()
    private val bdeApiService = BdeApiService()
    private val errorManager = ErrorManager()

    //REGISTER REQUEST
    suspend fun callRegisterRequest(userDto: UserDTO): Boolean {
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.USER, userDto, null)
        return errorManager.handleException(
            response,
            ErrorManager.ErrorType.ERROR
        )
    }

    //LOGIN REQUEST
    suspend fun callLoginRequest(loginDto: LoginDTO, context: Context): Boolean {
        //access to view model and service
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.LOGIN, loginDto,null)
        val success = errorManager.handleException(
            response,
            ErrorManager.ErrorType.ERROR
        )
        val connectUserJson = response.split(";")[1]
        val token = JSONObject(connectUserJson).getJSONObject("data").getString("token")
        val refreshToken = JSONObject(connectUserJson).getJSONObject("data").getString("refreshToken")

        //Save token in shared preference
        sharedPreferencesService.saveIn("TOKEN", token!!, context)
        sharedPreferencesService.saveIn(    "refreshToken", refreshToken!!, context)
        return success
    }

//    // EDIT USER REQUEST
//    suspend fun callEditUserRequest(editUserDTO: EditUserDTO, context: Context): Boolean{
//        // Get token current user
//        val sharedPreferencesService = SharedPreferencesService()
//        val token = sharedPreferencesService.retrived("TOKEN", context)
//        // Call API
//        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.EDIT_USER, editUserDTO, token)
//        val success = errorManager.handleException(
//            response,
//            ErrorManager.ErrorType.ERROR
//        )
//        return success
//    }

    //LOGOUT REQUEST
    suspend fun callLogoutRequest(context: Context): Boolean {
        val refreshToken = sharedPreferencesService.retrived("refreshToken", context)
        val token = sharedPreferencesService.retrived("TOKEN", context)
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.LOGOUT, refreshToken, token)
        return errorManager.handleException(
            response,
            ErrorManager.ErrorType.ERROR
        )
    }

    //DELETE USER REQUEST
    suspend fun callDeleteUserRequest(context: Context): Boolean {
        val token = sharedPreferencesService.retrived("TOKEN", context)
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.LOGOUT, null, token)
        return errorManager.handleException(
            response,
            ErrorManager.ErrorType.ERROR
        )
    }

    suspend fun meAndRefreshToken(context: Context){
        if(!callInformationUserRequest(context)){
            if(callRefreshRequest(context)){
                if(!callInformationUserRequest(context)){
                    throw Exception("Error me token")
                }
            }else{
                throw Exception("Error refresh token")
            }
        }
    }

    //ME REQUEST
    private suspend fun callInformationUserRequest(context: Context): Boolean {
        //access to view model and service
        val tokenOfConnect = sharedPreferencesService.retrived("TOKEN", context)
        if (tokenOfConnect.isNullOrEmpty()) {
            throw Exception("Aucun token")
        }
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.ME, null, tokenOfConnect)
        var error : String? = null
        if (!response.isNullOrEmpty()) {
            val t = response.split(";")
            val code = t[0].toInt()
            val json = t[1]
            val jsonObject = JSONObject(t[1])

            if (json.isEmpty()) {
                throw java.lang.Exception("Le Json récupéré est null")
            }
            if (code !in 200..299) {
                error = jsonObject.getJSONObject("error").getString("code")
                if(error == "INVALID_TOKEN"){
                    return false
                }
            }else{
                getUserInformations(response, context)
            }
        } else {
            throw java.lang.Exception("la reponse reçue est null")
        }

    return true
    }

    private fun getUserInformations(response: String, context: Context) {
        //Stock Informations user in Shared preferences
        val resultMe = response.split(";")[1]
        val jsonResultRequest = JSONObject(resultMe)
        val user = User(jsonResultRequest.getJSONObject("data").getString("_id"),
            jsonResultRequest.getJSONObject("data").getBoolean("isActive"),
            jsonResultRequest.getJSONObject("data").getBoolean("isAdmin"),
            jsonResultRequest.getJSONObject("data").getBoolean("isAdherent"),
            jsonResultRequest.getJSONObject("data").getString("firstName"),
            jsonResultRequest.getJSONObject("data").getString("lastName"),
            jsonResultRequest.getJSONObject("data").getString("mail"),
            jsonResultRequest.getJSONObject("data").getString("promotion"),
            jsonResultRequest.getJSONObject("data").getString("formation"))
        sharedPreferencesService.saveInUser(user, context)
    }

    //REFRESH REQUEST
    private suspend fun callRefreshRequest(context: Context): Boolean {
        val refreshToken = sharedPreferencesService.retrived("refreshToken", context)

        if (refreshToken.isNullOrEmpty()) {
            throw Exception("Aucun refresh token")
        }
        val response = bdeApiService.apiCaller(BdeApiService.NameRequest.REFRESH, refreshToken, null)
        Log.d("responseRefresh", response)
        if (!response.isNullOrEmpty()) {
            val code = response.split(";")[0].toInt()
            if (code !in 200..299) {
                return false
            } else {
                val json = response.split(";")[1]
                val token = JSONObject(json).getJSONObject("data").getString("token")
                val refreshToken = JSONObject(json).getJSONObject("data").getString("refreshToken")

                //Save token in shared preference
                sharedPreferencesService.saveIn("TOKEN", token!!, context)
                sharedPreferencesService.saveIn("refreshToken", refreshToken!!, context)
            }
        } else {
            throw java.lang.Exception("la reponse reçue est null")
        }
        return true
    }

}
