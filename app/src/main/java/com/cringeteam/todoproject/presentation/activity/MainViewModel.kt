package com.cringeteam.todoproject.presentation.activity

import com.cringeteam.todoproject.domain.usecases.GetProjectsUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.GroupVo
import com.cringeteam.todoproject.presentation.model.formatters.GroupFormatter
import io.reactivex.rxjava3.core.Single

class MainViewModel : BaseViewModel() {

    private val getProjectsUseCase = GetProjectsUseCase()
    private val groupFormatter = GroupFormatter()

    fun getProjects(): Single<List<GroupVo>> {
        return getProjectsUseCase.execute().map { groups ->
            groups.map { group ->
                groupFormatter.format(group)
            }
        }
    }
}