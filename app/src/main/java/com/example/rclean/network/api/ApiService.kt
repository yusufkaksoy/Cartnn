package com.example.rclean.network.api

import com.example.rclean.network.model.ResponseApi
import com.example.rclean.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page : Int,

    ) : Response<ResponseApi>
    @GET(Constants.LOCATION_BASE)
    suspend fun getLocations(
        @Query("id") id : Int,
    )

}