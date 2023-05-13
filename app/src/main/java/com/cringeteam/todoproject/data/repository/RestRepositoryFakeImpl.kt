package com.cringeteam.todoproject.data.repository

import com.cringeteam.todoproject.data.rest.model.GroupDto
import com.cringeteam.todoproject.data.rest.model.GroupMapper
import com.cringeteam.todoproject.data.rest.model.StatusMessageDto
import com.cringeteam.todoproject.data.rest.model.StatusMessageMapper
import com.cringeteam.todoproject.data.rest.model.UserDto
import com.cringeteam.todoproject.data.rest.model.UserMapper
import com.cringeteam.todoproject.data.rest.model.login.LoginRequestMapper
import com.cringeteam.todoproject.data.rest.model.login.LoginResponseDto
import com.cringeteam.todoproject.data.rest.model.login.LoginResponseMapper
import com.cringeteam.todoproject.data.rest.model.registration.RegistrationUserMapper
import com.cringeteam.todoproject.domain.model.Group
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.domain.model.RegistrationUser
import com.cringeteam.todoproject.domain.model.StatusMessage
import com.cringeteam.todoproject.domain.model.User
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

// TODO: Delete it when backend will be ready
class RestRepositoryFakeImpl : RestRepository {

    private val loginRequestMapper = LoginRequestMapper()
    private val responseMapper = LoginResponseMapper()

    private val registrationRequestMapper = RegistrationUserMapper()
    private val statusMessageMapper = StatusMessageMapper()

    private val groupMapper = GroupMapper()

    private val userMapper = UserMapper()

    override fun getLoginAccess(request: LoginRequest): Single<LoginResponse> {

        val requestDto = loginRequestMapper.map(request)

        val isAuthorizationCorrect = requestDto.login == "test" && requestDto.password == " "

        return if (isAuthorizationCorrect) {
            val fakeResponse = LoginResponseDto(
                token = "testToken",
                userId = -1L,
                login = "testLogin",
            )

            Single.just(responseMapper.map(fakeResponse))
                .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)

        } else {
            Single.error<LoginResponse>(Exception("Wrong login or Password"))
                .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)
        }
    }

    override fun registrationUser(request: RegistrationUser): Single<StatusMessage> {

        val requestDto = registrationRequestMapper.map(request)

        val isRegistrationUserCorrect =
            requestDto.login == "test"
                    && requestDto.password == " "
                    && requestDto.passwordConfirm == requestDto.password

        if (isRegistrationUserCorrect) {
            val fakeResponseDto = StatusMessageDto(
                codeStatus = RESPONSE_201,
                message = RESPONSE_MESSAGE_201
            )
            val fakeResponse = statusMessageMapper.map(fakeResponseDto)

            return Single.just(fakeResponse)
                .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)

        } else {
            val fakeResponseDto = StatusMessageDto(
                codeStatus = RESPONSE_400,
                message = RESPONSE_ERROR_400
            )
            val fakeResponse = statusMessageMapper.map(fakeResponseDto)

            return Single.just(fakeResponse)
                .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)
        }
    }

    override fun getGroups(): Single<List<Group>> {

        val fakeGroupsDtoList = List<GroupDto>(TEST_LIST_SIZE) { index ->
            GroupDto(0, "Project $index", "test")
        }

        val fakeGroupsList = fakeGroupsDtoList.map { dto ->
            groupMapper.map(dto)
        }

        return Single.just(fakeGroupsList)
            .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)
    }

    override fun getUser(): Single<User> {
        val fakeUserDto = UserDto("Test login")

        val fakeUser = userMapper.map(fakeUserDto)

        return Single.just(fakeUser)
            .delay(FAKE_REQUEST_DELAY, TimeUnit.SECONDS)
    }

    companion object {
        private const val FAKE_REQUEST_DELAY = 5L
        private const val TEST_LIST_SIZE = 10
        private const val RESPONSE_201 = 201
        private const val RESPONSE_MESSAGE_201 = "Создано"
        private const val RESPONSE_400 = 400
        private const val RESPONSE_ERROR_400 = "Некорректные данные, не хватает данных"
    }
}