package com.example.ynov_lyon_bde.domain.viewmodel.event

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventObject(
    var id: String,
    var name: String,
    var type: String,
    var date: String,
    var description: String,
    var hour:String,
    var address: String,
    //var imageEvent: Int
    ) : Parcelable
