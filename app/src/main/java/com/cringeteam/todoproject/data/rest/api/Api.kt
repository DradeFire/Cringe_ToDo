package com.cringeteam.todoproject.data.rest.api

import com.cringeteam.todoproject.common.consts.Consts
import com.cringeteam.todoproject.data.rest.model.login.LoginResponseDto
import com.cringeteam.todoproject.data.rest.model.registration.RegistrationUserDto
import com.cringeteam.todoproject.domain.model.StatusMessage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("${Consts.URL_PREFIX}/auth/login")
    fun getLoginAccess(
        @Query("login") login: String,
        @Query("password") password: String,
    ): Single<LoginResponseDto>

    @POST("${Consts.URL_PREFIX}/auth/registration")
    fun signUp(@Body registrationUserDto: RegistrationUserDto): Single<StatusMessage>
}