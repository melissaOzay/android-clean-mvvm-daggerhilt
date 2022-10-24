package com.example.mvvmapp.data.api

import com.example.mvvmapp.data.entities.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("post")
    fun addUser(@Body userData: UserInfo): Call<List<UserInfo>>

    @GET("get")
    fun getUser(): Call<List<UserInfo>>

    @GET("search")
    fun getMovies(@Query("name") name: String): Call<List<UserInfo>>
}
