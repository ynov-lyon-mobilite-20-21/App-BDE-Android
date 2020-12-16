package com.example.ynov_lyon_bde.domain.viewmodel.onBoarding

import android.app.Activity
import android.content.Context

class OnBoardingViewModel {

    fun onBoardingFinished(activity: Activity): Boolean{
        val sharedPref = activity.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }
}
