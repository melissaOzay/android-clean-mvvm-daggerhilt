package com.example.mvvmapp.domain.repository

import com.example.mvvmapp.data.entities.UserInfo


interface UserListInterface {
    fun success(userList: List<UserInfo>)
    fun fail(message: String)
}
