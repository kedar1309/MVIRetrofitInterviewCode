package com.example.myapplication.presentation

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.UIResult
import com.example.myapplication.domain.UserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val usecase: UserUsecase) : ViewModel() {

    private var _state: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    var state : StateFlow<UserState> = _state


    fun processIntent(userIntent: UserIntent){
        when(userIntent){
            UserIntent.LoadUser -> loadUser()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            usecase().collect { result ->
                _state.update { current ->
                    when (result) {
                        is UIResult.Loading -> current.copy(isLoading = true)

                        is UIResult.Success -> current.copy(
                            isLoading = false,
                            userList = result.data,
                            error = null
                        )

                        is UIResult.Failure -> current.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }


}