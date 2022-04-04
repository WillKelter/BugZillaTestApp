package com.willkelter.bugzillatestapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.willkelter.bugzillatestapp.data.BugModel


@Composable
fun DetailScreen(bugModel: BugModel){
    val style = MaterialTheme.typography.h5
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center){

        Column(){
            Text(text = "id: ${bugModel.id}", style = style)
            Text(text = "creator: ${bugModel.creator}", style = style)
            Text(text = "summary: ${bugModel.summary}", style = style)
            Text(text = "product: ${bugModel.product}", style = style)
            Text(text = "component: ${bugModel.component}", style = style)
            Text(text = "classification: ${bugModel.classification}", style = style)
            Text(text = "status: ${bugModel.status}", style = style)
            Text(text = "is confirmed: ${bugModel.is_confirmed}", style = style)
            Text(text = "priority: ${bugModel.priority}", style = style)
            Text(text = "creation time: ${bugModel.creation_time}", style = style)
        }
    }
}



@Composable
@Preview(showBackground = true)
fun  DetailScreenPreview() {
    DetailScreen(bugModel = BugModel(1, "2", "3", "4", "5", "6", true, "8", "9", "component"))
}