package com.willkelter.bugzillatestapp.data.domain

import com.willkelter.bugzillatestapp.data.BugModel

//Online request repository interface
interface BugsRepository {
     fun getBugs(onSuccess:(bugs: List<BugModel>)-> Unit, onError: () -> Unit)
}