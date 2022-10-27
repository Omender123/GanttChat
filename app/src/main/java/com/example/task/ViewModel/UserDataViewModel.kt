package com.example.task.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task.Repositories.UserDataRepositories
import com.example.task.Response.ApiData
import com.example.task.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserDataViewModel @Inject() constructor(
    private val userDataRespository: UserDataRepositories,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ApiData>> = MutableLiveData()
    val GetListResponse: LiveData<NetworkResult<ApiData>> = _response

    fun fetchListDataResponse(email: String, password: String, role: String) =
        viewModelScope.launch {
            userDataRespository.getUserLoginData(email, password, role).collect { values ->
                _response.value = values
            }
        }

}