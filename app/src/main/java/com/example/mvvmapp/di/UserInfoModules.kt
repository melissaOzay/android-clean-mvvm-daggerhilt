package com.example.mvvmapp.di

import com.example.mvvmapp.BuildConfig
import com.example.mvvmapp.data.api.RestApi
import com.example.mvvmapp.data.api.UserRepositoryImpl
import com.example.mvvmapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoModules {
    @Provides
    @Singleton
    fun providerGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://melisa-test.herokuapp.com/api/test/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    }
    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUserInfoRepository(apiService: RestApi): UserRepository {
        return UserRepositoryImpl(apiService)


        //nesnelerin oluşturma kurallarının belirlendiği yer
    }
}