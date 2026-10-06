package com.zhuonan.netflix.network

import com.zhuonan.netflix.datamodel.HomeResponse
import com.zhuonan.netflix.datamodel.VideoDetailRequest
import com.zhuonan.netflix.datamodel.VideoDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.zhuonan.netflix.datamodel.ProfileResponse

// Defines the backend endpoints the app can call in this class.
// Defines the backend endpoints the app can call in this class.
interface NetworkApi {
    @GET("/home")
    fun getHome(): Call<HomeResponse>

    @POST("/videoDetail")
    fun getDetail(@Body request: VideoDetailRequest): Call<VideoDetailResponse>

    @GET("/profile")
    fun getProfile(): Call<ProfileResponse>
}


