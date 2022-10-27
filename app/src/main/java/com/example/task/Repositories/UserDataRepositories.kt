package com.example.task.Repositories

import com.example.task.Response.ApiData
import com.example.task.Response.BaseApiResponse
import com.example.task.Utils.NetworkResult
import com.example.task.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class UserDataRepositories @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun getUserLoginData(email:String, password:String, role:String): Flow<NetworkResult<ApiData>> {
        return flow<NetworkResult<ApiData>> {
            emit(safeApiCall { remoteDataSource.Login(email, password, role) })
        }.flowOn(Dispatchers.IO)
    }
}