package com.example.myapplication.presentation

import com.example.myapplication.data.UserDTO

data class UserState (

    val isLoading : Boolean = false,
     val userList : List<UserDTO> = emptyList(),
    val error: String? = null

)