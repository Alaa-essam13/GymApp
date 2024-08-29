package com.example.gymapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun GymScreen(modifier: Modifier = Modifier) {
    val vm: GymViewmodel = viewModel()
    LazyColumn {
        items(vm.state) {gym->
            GymItem(gym){
             vm.toggleFavState(it)
            }
        }
    }
}

@Composable
fun GymItem(gym: Gym,onClick: (Int) -> Unit) {
    val icon =if(gym.isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.Place, Modifier.weight(0.15f), "Location Icon")
            GymDetails(gym, Modifier.weight(0.70f))
            DefaultIcon(icon, Modifier.weight(0.15f), "fav Icon"){
                onClick(gym.id)
            }
        }
    }
}

@Composable
fun GymDetails(gym: Gym, modifier: Modifier=Modifier) {
    Column(modifier=modifier) {
        Text(text = gym.name, style = MaterialTheme.typography.headlineLarge)
        Text(text = gym.name, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.alpha(.5f))
    }
}

@Composable
fun DefaultIcon(img: ImageVector, modifier: Modifier=Modifier, description: String,onClick:()->Unit={}) {
    Image(imageVector = img, contentDescription = description, modifier =modifier.clickable { onClick() })
}


@Preview(showSystemUi = true)
@Composable
private fun test() {
    GymScreen()
}