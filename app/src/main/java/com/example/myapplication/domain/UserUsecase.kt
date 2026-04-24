package com.example.myapplication.domain

import com.example.myapplication.data.UserDTO
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import okio.IOException
import javax.inject.Inject
import kotlin.math.pow
import kotlin.random.Random

class UserUsecase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke() : Flow<UIResult< List<UserDTO>>>{
        return flow {
            emit(UIResult.Loading)
            val data = userRepository.getUserList()
            emit(UIResult.Success(data))
        }.retryWhen { cause, attempt ->
            if(attempt <= 3 && cause is IOException){
                var d = 2.0.pow((attempt.toDouble() * 1000L)).toLong()
                var jitter = Random.nextLong(0, 500)
                delay(d+ jitter)
                true
            }else {
                false
            }
        }.catch { e ->
            emit(UIResult.Failure(e.message.toString()))
        }
    }

}