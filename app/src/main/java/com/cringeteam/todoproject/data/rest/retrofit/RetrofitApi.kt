package com.cringeteam.todoproject.data.rest.retrofit

import com.cringeteam.todoproject.common.consts.Consts
import com.cringeteam.todoproject.data.rest.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {

    val retrofit: Api = Retrofit
        .Builder()
        .baseUrl(Consts.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}