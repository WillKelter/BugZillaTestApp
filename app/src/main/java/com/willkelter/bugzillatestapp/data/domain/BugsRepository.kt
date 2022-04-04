package com.willkelter.bugzillatestapp.data.domain

import com.willkelter.bugzillatestapp.data.BugModel

interface BugsRepository {
     fun getBugs(onSuccess:(bugs: List<BugModel>)-> Unit, onError: () -> Unit)
}