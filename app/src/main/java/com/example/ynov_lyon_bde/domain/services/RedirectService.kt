package com.example.ynov_lyon_bde.domain.services

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import com.example.ynov_lyon_bde.ui.screens.connection.signIn.SignInFragment
import com.example.ynov_lyon_bde.ui.screens.profil.ProfileFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class RedirectService {


fun redirect(context: Context): Intent {

    val authenticationViewModel = AuthenticationRequests()
    var intent = Intent().setClass(context, SignInFragment::class.java)
    val sharedPreferencesService = SharedPreferencesService()

    var errorGetUser: String? = null
    var errorRefresh: String? = null
    if (verifStorageUser()) {
        intent = Intent().setClass(context, ProfileFragment::class.java)
    } else {
        if (sharedPreferencesService.retrived("TOKEN", context).isNullOrEmpty()) {
           intent = Intent().setClass(context, SignInFragment::class.java)
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                val deferred = async(Dispatchers.IO) {
                    //call requests
                    try {
                        authenticationViewModel.callInformationUserRequest(context)
                    } catch (err: Exception) {
                        errorGetUser = err.message
                        Log.e("error", errorGetUser)
                    }
                    if (errorGetUser == "INVALID_TOKEN") {
                        try {
                            authenticationViewModel.callRefreshRequest(context)
                        } catch (err: Exception) {
                            errorRefresh = err.message
                            Log.e("error", errorRefresh)
                        }
                        if (errorRefresh.isNullOrEmpty()) {
                            try {
                                errorGetUser = null
                                authenticationViewModel.callInformationUserRequest(context)
                            } catch (err: Exception) {
                                errorGetUser = err.message
                                Log.e("error", errorGetUser)
                                intent = Intent().setClass(context, SignInFragment::class.java)
                            }
                        }
                    }
                }
                deferred.await()
            }
            if (errorGetUser == null) {
                intent = Intent().setClass(context, ProfileFragment::class.java)
            }
            if (errorRefresh != null) {
                intent = Intent().setClass(context, SignInFragment::class.java)
            }
        }
    }
    Log.e("intent", intent.toString())
    return intent
}

    fun  verifStorageUser() : Boolean {
        val userExist : Boolean = false
        //TODO Verif user exist
        return userExist
    }


}
