package com.test.mvvmtest.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.test.mvvmtest.model.PostCallFromServer
import com.test.mvvmtest.ui.MainActivityViewModelFactory

object injection {
    private val _postCall:PostCallFromServer = PostCallFromServer()
     fun providePostViewModelFactory(context : Context): ViewModelProvider.Factory{
        return MainActivityViewModelFactory(context,_postCall)
    }
}