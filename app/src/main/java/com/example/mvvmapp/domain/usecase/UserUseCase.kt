package com.example.mvvmapp.domain.usecase

import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.data.repository.UserRepository
import com.example.mvvmapp.domain.entities.UserUIModel
import com.example.mvvmapp.domain.mapper.toUserUIModel
import com.example.mvvmapp.domain.repository.CreateNewUserInterface
import com.example.mvvmapp.domain.repository.SearchInterface
import com.example.mvvmapp.domain.repository.UserListInterface
import okhttp3.Response
import javax.inject.Inject

 class UserUseCase  @Inject constructor(
    private val userRepository: UserRepository) {
    fun createNewUser(data: UserInfo, createNewUserInterface: CreateNewUserInterface) =
        userRepository.createNewUser(data, createNewUserInterface)

    fun loadData(userListInterface: UserListInterface) =
        userRepository.loadData(userListInterface)

    fun searchAfter(name: String, searchInterface: SearchInterface) =
        userRepository.searchAfter(name, searchInterface)
    fun toUIModel(response: List<UserInfo>?): List<UserUIModel>? {
        return response?.map {
            it.toUserUIModel()
        }
    }
}

