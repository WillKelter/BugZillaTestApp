package com.willkelter.bugzillatestapp.data.remote



import com.willkelter.bugzillatestapp.data.GetBugsResponse
import retrofit2.Call
import retrofit2.http.GET

//Retrofit request interface
interface ServiceApi {
   @GET("bug")
    fun getBugs(): Call<GetBugsResponse>

}