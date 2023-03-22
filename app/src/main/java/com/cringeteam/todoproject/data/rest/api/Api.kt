package com.cringeteam.todoproject.data.rest.api

import com.cringeteam.todoproject.data.dto.loginResponse.LoginResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET
    fun getLoginAccess(
        @Query("login") login: String,
        @Query("password") password: String,
    ): Single<LoginResponseDto>
}