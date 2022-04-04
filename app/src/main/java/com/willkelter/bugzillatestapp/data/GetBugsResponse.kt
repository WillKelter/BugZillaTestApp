package com.willkelter.bugzillatestapp.data

import com.google.gson.annotations.SerializedName

data class GetBugsResponse(
    @SerializedName("bugs")
    val bugs: List<BugModel>
)