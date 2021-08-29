package com.test.mvvmtest.retro

import com.test.mvvmtest.datamodel.Postresponse
import com.test.mvvmtest.model.PostCallFromServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
object ApiClient {
    private val API_BASE_URL = "http://www.mocky.io/v2/"
    private var servicesApiInterface:ServicesApiInterface?=null
    fun build():ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())
        httpClient.connectTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
        httpClient.readTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)
        return servicesApiInterface as ServicesApiInterface
    }
    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
    interface ServicesApiInterface{
        @GET("59b3f0b0100000e30b236b7e")
        fun getPostData(): Call<Postresponse>
    }
}