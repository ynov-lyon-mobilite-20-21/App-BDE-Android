package com.example.ynov_lyon_bde.domain.services

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.navigation.findNavController
import com.example.ynov_lyon_bde.domain.services.request.AuthenticationRequests
import com.example.ynov_lyon_bde.ui.screens.connection.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class RedirectConnectService {
    enum class TypeAlertDialog {
        PROFIL, ACHAT
    }
    fun redirect(context: Context, activity: Activity, typeAlertDialog: TypeAlertDialog) {
        val authenticationRequests = AuthenticationRequests()
        var needConnect = false
        val sharedPreferencesService = SharedPreferencesService()

        if (verifyStorageUser(context)) {
            needConnect = false
        } else {
            if (sharedPreferencesService.retrived("TOKEN", context).isNullOrEmpty()) {
                needConnect = true
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    val deferred = async(Dispatchers.IO) {
                        //call requests
                        try {
                            authenticationRequests.meAndRefreshToken(context)
                        } catch (err: Exception) {
                            needConnect = true
                            Log.e("redirectConnect", err.toString())
                        }
                    }
                    deferred.await()
                }
            }
        }
        if(needConnect){
            onAlertDialog(context, activity, typeAlertDialog)
        }
    }

    private fun onAlertDialog(context: Context, activity: Activity, typeAlertDialog: TypeAlertDialog) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Vous n'êtes pas connecté")
        if(typeAlertDialog == TypeAlertDialog.PROFIL){
            builder.setMessage("Connectez-vous à un compte Ynov pour consulter votre profil")
        }else if(typeAlertDialog == TypeAlertDialog.ACHAT){
            builder.setMessage("Connectez-vous à un compte Ynov pour acheter un billet")
        }

        builder.setPositiveButton("Connexion") { dialog, which ->
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
        builder.setNegativeButton("Annuler") { dialog, which ->
            activity.base_nav_host.findNavController().popBackStack()
        }
        builder.show()
    }

    private fun verifyStorageUser(context: Context) : Boolean {
        val sharedPreferencesService = SharedPreferencesService()
        var userExist = false
        if (sharedPreferencesService.retrivedUser(context) != null){
            userExist = true
        }
        return userExist
    }


}
