package com.willkelter.bugzillatestapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.ui.theme.BugZillaTestAppTheme
import com.willkelter.bugzillatestapp.utils.Screen
import com.willkelter.bugzillatestapp.view.viewmodels.BugsViewModel

@Composable
fun MainScreen(viewModel: BugsViewModel, navController: NavController){
    BugList(bugs = viewModel.bugListResponse, navController)
}

@Composable
fun BugList(bugs: List<BugModel>, navController: NavController) {

    LazyColumn(){
        items(bugs){
                bug ->  BugCard(bug.id, bug.summary, bug.creator, bug.product, bug.status, bug.creation_time
        ) { navController.currentBackStackEntry?.savedStateHandle?.set("bug", bug)
            navController.navigate(Screen.Detail.route) }

        }
        }
    }


@Composable
fun BugCard(id: Int, summary: String, creator: String, product: String, status: String, creationTime: String, onClick: () -> Unit){
    Card(shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(10.dp)
            .clickable { onClick() },
        elevation = 6.dp)
    {

        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(10.dp)) {

            Text(text = "#$id $summary", style = MaterialTheme.typography.h5)
            Text(text = "creator: $creator", style = MaterialTheme.typography.body1, modifier = Modifier.padding(top = 5.dp))
            Text(text = "product: $product", style = MaterialTheme.typography.body2, modifier = Modifier.padding(top = 5.dp))
            Text(text = "status: $status", style = MaterialTheme.typography.body2,modifier = Modifier.padding(top = 5.dp))
            Text(text = creationTime, style = MaterialTheme.typography.body2,modifier = Modifier.padding(top = 10.dp))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BugZillaTestAppTheme {
        BugCard(1,"deus","lorem", "ipsum", "dolor", "10.10.21 23:47", {})
    }
}