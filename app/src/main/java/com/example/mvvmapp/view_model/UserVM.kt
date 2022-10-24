package com.example.mvvmapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.domain.repository.SearchInterface
import com.example.mvvmapp.domain.repository.UserListInterface
import com.example.mvvmapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(val userUseCase: UserUseCase) : ViewModel() {

    val userListInfo = MutableLiveData<List<UserInfo>>()
    val fail = MutableLiveData<String>()
    val searchList = MutableLiveData<List<UserInfo>>()
    val failure = MutableLiveData<String>()

    fun loadData() {
        userUseCase.loadData(object : UserListInterface {
            override fun success(userList: List<UserInfo>) {
                userListInfo.postValue(userList)
            }

            override fun fail(message: String) {
                fail.postValue(message)
            }

        })
    }

    fun searchAfter(searchAfter: String){
        userUseCase.searchAfter(searchAfter,object : SearchInterface {
         override fun onSuccess(data: List<UserInfo>) {
             searchList.postValue(data)
         }

         override fun onFail(message: String) {
             failure.postValue(message)
         }

     })
    }
}









