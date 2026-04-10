package com.example.myapplication.data

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService): UserRepository{

    override suspend fun getUserList(): List<UserDTO> {
        return apiService.getUserList()
    }

}
