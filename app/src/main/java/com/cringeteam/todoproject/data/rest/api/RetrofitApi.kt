package com.cringeteam.todoproject.data.rest.api

import com.cringeteam.todoproject.common.consts.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {

    val retrofit: Api = Retrofit
        .Builder()
        .baseUrl(Consts.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}