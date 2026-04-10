package com.example.myapplication.data

interface UserRepository {

   suspend fun getUserList(): List<UserDTO>
}