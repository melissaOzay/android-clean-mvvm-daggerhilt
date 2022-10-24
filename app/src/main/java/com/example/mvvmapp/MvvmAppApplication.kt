package com.example.mvvmapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MvvmAppApplication  : Application(){
    //Bu olmazsa dagger hilt hata verir.
}