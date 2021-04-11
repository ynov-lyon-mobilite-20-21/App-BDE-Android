package com.example.ynov_lyon_bde.domain.viewmodel.scanner


import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.data.model.Event
import com.example.ynov_lyon_bde.domain.services.SharedPreferencesService
import com.example.ynov_lyon_bde.domain.services.request.CheckTicketRequest
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*


class QRScannerViewModel: ViewModel() {

    var token:String? = null
    private val sharedPreferencesService = SharedPreferencesService()

    fun printNameOfClient(view: View, it: String?){
        view.name_person.text = it
    }

    fun changeColorBorderCard(message: String?, view: View, activity: Activity, context: Context?){
        val cardCamera = view.card_scanner
        if (message == "Billet non reconnu"){
            val color: Int = R.color.qrcode_border_red
            activity.runOnUiThread { cardCamera.foreground = context?.let { context -> ContextCompat.getColor(context, color) }?.let { colorInt -> ColorDrawable(colorInt) }}
        }else{
            val color: Int = R.color.qrcode_border_blue
            activity.runOnUiThread { cardCamera.foreground = context?.let { context -> ContextCompat.getColor(context, color) }?.let { it2 -> ColorDrawable(it2) }}
        }
    }

    suspend fun validationTicket(uid: String, context: Context) : String?{
        token = sharedPreferencesService.retrived("TOKEN", context)

        var message:String? = null
        val checkTicketRequest = CheckTicketRequest()
        message = try {
            checkTicketRequest.callTicketRequest(uid, token)
        }
        catch (err: Exception){
            "Billet non reconnu"
        }
        return message
    }
}
