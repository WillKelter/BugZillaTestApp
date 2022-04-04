package com.willkelter.bugzillatestapp.data.domain

import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.data.GetBugsResponse
import com.willkelter.bugzillatestapp.data.remote.ServiceApi
import com.willkelter.bugzillatestapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BugsRepositoryImpl: BugsRepository {

    private val api : ServiceApi

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ServiceApi::class.java)

    }

    override fun getBugs(onSuccess: (bugs: List<BugModel>) -> Unit, onError: () -> Unit) {
        api.getBugs()
            .enqueue(object: Callback<GetBugsResponse>{
                override fun onResponse(
                    call: Call<GetBugsResponse>,
                    response: Response<GetBugsResponse>
                ) {
                    getResponse(response, onSuccess, onError)
                }

                override fun onFailure(call: Call<GetBugsResponse>, t: Throwable) {
                    onError.invoke()
                }

            })
    }

    private fun getResponse(response : Response<GetBugsResponse>, onSuccess: (bugs: List<BugModel>)-> Unit, onError: () -> Unit)
    {
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                onSuccess.invoke(responseBody.bugs)
            } else {
                onError.invoke()
            }
        }
        else {
            onError.invoke()
        }
    }
}