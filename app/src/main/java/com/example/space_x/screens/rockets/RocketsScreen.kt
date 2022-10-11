package com.example.space_x.screens.rockets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.space_x.R
import com.example.space_x.domain.rocket.Rocket
import com.example.space_x.navigation.NavigationScreens
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.others.AppBar
import com.example.space_x.others.composable.LoadingWrapper
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel){
    val viewModel by viewModel<RocketsViewModel>()
    val rocketsState = viewModel.rockets.observeAsState()

    Scaffold(topBar ={
        AppBar(
            title = stringResource(R.string.rockets),
            drawerState = sharedViewModel.drawerState,
            options = sharedViewModel.topBarOptions
        )
    }
    ) { padding ->
        Box(Modifier.padding(padding)){
            if(rocketsState.value != null){
                LoadingWrapper(loadingState = rocketsState.value!!) { list ->
                    RocketsList(rockets = list, navHostController)
                }
            }
        }
    }
}

@Composable
fun RocketsList(rockets: List<Rocket>, navHostController: NavHostController){
    LazyColumn(Modifier.padding(10.dp)){
        items(rockets.size){ index ->
            RocketPreview(rocket = rockets[index]){
                // Navigate to the rocket detail screen
                navHostController.navigate(NavigationScreens.RocketDetailScreen.route + "/${rockets[index].id}")
            }
        }
    }
}

@Composable
fun RocketPreview(rocket: Rocket, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                onClick()
            }
    ) {
        // Sets image when not null else uses default rocket image
        val imageModel =  if(rocket.flickrImages != null && rocket.flickrImages.isNotEmpty()){
           rocket.flickrImages[0]
        }else{
            R.drawable.default_rocket
        }

        AsyncImage(
            model = imageModel,
            contentDescription = null,
            error = painterResource(id = R.drawable.default_rocket),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.66F)
        )

        Column() {
            rocket.name?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp, top = 6.dp)
                )
            }
            rocket.country?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 6.dp, top = 6.dp)
                )
            }
        }
    }
}