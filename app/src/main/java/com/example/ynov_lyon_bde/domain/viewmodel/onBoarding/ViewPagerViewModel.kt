package com.example.ynov_lyon_bde.domain.viewmodel.onBoarding

import android.app.Activity
import android.content.Context
import androidx.viewpager2.widget.ViewPager2

class ViewPagerViewModel {

     fun goToNext(viewPager: ViewPager2) {
         viewPager.currentItem++
     }

     fun onBoardingIsFinished(activity: Activity){
        val sharedPref = activity.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}
