package com.test.mvvmtest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mvvmtest.repository.PostRepository

class MainActivityViewModelFactory(var context: Context, private var repository: PostRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainactivityViewModel(context,repository) as T
    }

}