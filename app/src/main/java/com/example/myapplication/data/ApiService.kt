package com.example.myapplication.data

import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUserList(): List<UserDTO>
}