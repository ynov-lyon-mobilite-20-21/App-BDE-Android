package com.example.ynov_lyon_bde.domain.viewmodel.scanner


import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.data.model.Event
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*


class QRScannerViewModel {

    fun printTitleEvent (event: Event, view:View){
        view.title_event_qrcode.text = event.name
    }

    fun printNameOfClient(view: View,it:String?){
        view.name_person.text = it
    }

    fun changeColorBorderCard(message:String?,view:View,activity:Activity,context:Context?){
        val cardCamera = view.card_scanner
        if (message == "Billet non reconnu"){
            val color: Int = R.color.qrcode_border_red
            activity?.runOnUiThread { cardCamera.foreground = context?.let { it1 -> ContextCompat.getColor(it1, color) }?.let { it2 -> ColorDrawable(it2) }}
        }else{
            val color: Int = R.color.qrcode_border_blue
            activity?.runOnUiThread { cardCamera.foreground = context?.let { it1 -> ContextCompat.getColor(it1, color) }?.let { it2 -> ColorDrawable(it2) }}
        }
    }
}
