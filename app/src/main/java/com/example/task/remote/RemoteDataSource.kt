package com.example.task.remote


import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun Login(email:String, password:String, role:String) =
        apiService.Login(email, password, role)

}