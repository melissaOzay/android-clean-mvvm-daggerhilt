package com.example.mvvmapp.data.api

import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.data.repository.UserRepository
import com.example.mvvmapp.domain.repository.CreateNewUserInterface
import com.example.mvvmapp.domain.repository.SearchInterface
import com.example.mvvmapp.domain.repository.UserListInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: RestApi): UserRepository {
    override fun createNewUser(userData: UserInfo, createNewUserInterface: CreateNewUserInterface) {
        apiService.addUser(userData).enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()
                    createNewUserInterface.onSuccess(response.body()!!)

                } else {
                    createNewUserInterface.onFail(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                createNewUserInterface.onFail(t.message!!)
            }
        })
    }

    override fun loadData(userListInterface: UserListInterface) {
        apiService.getUser().enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    userListInterface.success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                userListInterface.fail(t.localizedMessage!!)
            }
        })
    }

    override fun searchAfter(searchAfter: String, searchAfterInterface: SearchInterface) {
        if (searchAfter.length == 2||searchAfter.length==0){
            apiService.getMovies(searchAfter).enqueue(object : Callback<List<UserInfo>> {

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    if (response.isSuccessful) {
                        searchAfterInterface.onSuccess(response.body()!!)

                    }
                }

                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                    searchAfterInterface.onFail(t.message!!)

                }
            })
        }
    }

}