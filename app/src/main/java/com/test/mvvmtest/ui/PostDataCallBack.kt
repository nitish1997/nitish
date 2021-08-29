package com.test.mvvmtest.ui

interface PostDataCallBack<T> {
    fun OnSuccess(Data:T?)
    fun OnError(Error:String?)
}