package com.cringeteam.todoproject.presentation.features.profileScreen

import com.cringeteam.todoproject.domain.usecases.GetProjectsUseCase
import com.cringeteam.todoproject.domain.usecases.GetUserUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.GroupVo
import com.cringeteam.todoproject.presentation.model.UserVo
import com.cringeteam.todoproject.presentation.model.formatters.GroupFormatter
import com.cringeteam.todoproject.presentation.model.formatters.UserFormatter
import io.reactivex.rxjava3.core.Single

class ProfileViewModel : BaseViewModel() {

    private val getProjectsUseCase = GetProjectsUseCase()
    private val getUserUseCase = GetUserUseCase()

    private val groupFormatter = GroupFormatter()
    private val userFormatter = UserFormatter()

    fun getUser(): Single<UserVo> {
        return getUserUseCase.execute()
            .map { user ->
                userFormatter.format(user)
            }
    }

    fun getProjects(): Single<List<GroupVo>> {
        return getProjectsUseCase.execute()
            .map { groups ->
                groups.map { group ->
                    groupFormatter.format(group)
                }
            }
    }
}