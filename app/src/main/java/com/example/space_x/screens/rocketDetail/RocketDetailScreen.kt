package com.example.space_x.screens.rocketDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.space_x.R
import com.example.space_x.domain.rocket.Rocket
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.others.*
import com.example.space_x.others.composable.LoadingWrapper
import com.example.space_x.others.composable.LongText
import com.example.space_x.others.composable.Subtitle
import com.example.space_x.others.composable.Title
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailScreen(rocketId: String?, sharedViewModel: SharedViewModel){
    val viewModel by viewModel<RocketDetailViewModel>{ parametersOf(rocketId)}
    val rocketState = viewModel.rocket.observeAsState()


    Scaffold(topBar ={
        AppBar(
            title = stringResource(id = R.string.space_x),
            drawerState = sharedViewModel.drawerState,
            options = sharedViewModel.topBarOptions
        )
    }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            if(rocketState.value != null) {
                LoadingWrapper(loadingState = rocketState.value!!) { rocket ->
                    RocketDetail(rocket = rocket, viewModel.unitSystem)
                }
            }
        }
    }
}

@Composable
fun RocketDetail(rocket: Rocket, unitSystem: UnitSystem){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        rocket.name?.let {
            Title(title = it)
        }


        rocket.description?.let {
            LongText(text = it)
        }

        if(rocket.flickrImages != null && rocket.flickrImages.isNotEmpty()){
            Gallery(images = rocket.flickrImages)
        }

        Subtitle(title = stringResource(R.string.about_rocket))

        rocket.type?.let { InfoProperty(label = stringResource(R.string.type), value = it) }

        rocket.mass?.let { InfoProperty(
            label = stringResource(R.string.mass),
            value = "${it.getBySystem(unitSystem)} ${stringResource(id = unitSystem.massUnitId)}"
        )}

        rocket.height?.let { InfoProperty(
            label = stringResource(R.string.height),
            value = "${it.getBySystem(unitSystem)} ${stringResource(id = unitSystem.lengthUnitId)}"
        )}

        rocket.diameter?.let { InfoProperty(
            label = stringResource(R.string.diameter),
            value = "${it.getBySystem(unitSystem)} ${stringResource(id = unitSystem.lengthUnitId)}"
        )}

        rocket.country?.let { InfoProperty(label = stringResource(R.string.country), value = it) }
        rocket.company?.let { InfoProperty(label = stringResource(R.string.company), value = it) }
        rocket.wikipedia?.let { InfoProperty(label = stringResource(R.string.more_at), value = it) }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Gallery(images: List<String>){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        // Pager with images
        val pagerState = rememberPagerState()
        HorizontalPager(count = images.size, state = pagerState) { index ->
            AsyncImage(
                model = images[index],
                contentDescription = null,
                error = painterResource(id = R.drawable.default_rocket),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        // Pager indicator
        Box(modifier = Modifier.fillMaxWidth()) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}