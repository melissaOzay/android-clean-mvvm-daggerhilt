package com.example.mvvmapp.domain.repository

import com.example.mvvmapp.data.entities.UserInfo

interface CreateNewUserInterface {
    fun onSuccess(data: List<UserInfo>)
    fun onFail(message: String)
}