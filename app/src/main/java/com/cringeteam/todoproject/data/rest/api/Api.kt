package com.cringeteam.todoproject.data.rest.api

import com.cringeteam.todoproject.common.consts.Consts
import com.cringeteam.todoproject.data.rest.model.loginResponse.LoginResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("${Consts.URL_PREFIX}/auth/login")
    fun getLoginAccess(
        @Query("login") login: String,
        @Query("password") password: String,
    ): Single<LoginResponseDto>
}