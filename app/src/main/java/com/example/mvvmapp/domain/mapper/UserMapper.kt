package com.example.mvvmapp.domain.mapper

import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.domain.entities.UserUIModel

fun UserInfo.toUserUIModel(): UserUIModel= UserUIModel(
    this.name,
    this.surname,
    this.email,
    this.password
)
