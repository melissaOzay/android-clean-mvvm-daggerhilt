package com.example.mvvmapp.view_model


import androidx.lifecycle.*
import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.domain.repository.CreateNewUserInterface
import com.example.mvvmapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveVM @Inject constructor(
    private val  userUseCase: UserUseCase) : ViewModel() {

    var failure = MutableLiveData<String>()
    val userListInfo = MutableLiveData<List<UserInfo>>()

    fun createNewUser(data: UserInfo) {
        userUseCase.createNewUser(data, object : CreateNewUserInterface {
            override fun onSuccess(data: List<UserInfo>) {
                userListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failure.postValue(message)
            }

        })
    }


}









