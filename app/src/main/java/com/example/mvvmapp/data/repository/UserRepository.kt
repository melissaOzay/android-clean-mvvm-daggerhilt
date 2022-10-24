package com.example.mvvmapp.data.repository

import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.domain.repository.CreateNewUserInterface
import com.example.mvvmapp.domain.repository.SearchInterface
import com.example.mvvmapp.domain.repository.UserListInterface


interface UserRepository {
    fun createNewUser(userData: UserInfo, createNewUserInterface: CreateNewUserInterface)
    fun loadData(userListInterface: UserListInterface)
    fun searchAfter(searchAfter: String, searchAfterInterface: SearchInterface)
}