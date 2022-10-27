package com.example.task.remote


import com.example.task.Response.ApiData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun Login(
       @Field("email") email:String,
       @Field("password") password:String,
       @Field("role") role:String
    ): Response<ApiData>


}

