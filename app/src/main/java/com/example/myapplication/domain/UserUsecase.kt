package com.example.myapplication.domain

import com.example.myapplication.data.UserDTO
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUsecase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke() : Flow<UIResult< List<UserDTO>>>{

        return flow {
            emit(UIResult.Loading)
            try {
               val data = userRepository.getUserList()
                emit(UIResult.Success(data))
            }catch (e: Exception){
                emit(UIResult.Failure(e.message.toString()))
            }
        }
    }

}