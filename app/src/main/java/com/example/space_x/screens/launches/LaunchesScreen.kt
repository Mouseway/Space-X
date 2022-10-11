package com.example.space_x.screens.launches

import android.app.DatePickerDialog
import android.provider.CalendarContract
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.space_x.R
import com.example.space_x.domain.launch.Launch
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.others.AppBar
import com.example.space_x.others.composable.RadioButtonGroupDefaults
import com.example.space_x.others.composable.RadiobuttonGroup
import org.koin.androidx.compose.viewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesScreen(sharedViewModel: SharedViewModel) {
    val viewModel by viewModel<LaunchesViewModel>()

    val launches: LazyPagingItems<Launch> = viewModel.launches.collectAsLazyPagingItems()

    val filtering = remember {
        MutableTransitionState(false).apply {
            targetState = false
        }
    }

    Scaffold(topBar ={
        AppBar(
            title = stringResource(R.string.launches),
            drawerState = sharedViewModel.drawerState,
            options = sharedViewModel.topBarOptions,
            filterOnClick = {
                filtering.targetState = !filtering.targetState
            }
        )
    }
    ) { padding ->

        Column(Modifier.padding(padding)) {
            // Showing filters with animation
            AnimatedVisibility(
                visibleState = filtering,
                enter = expandVertically(expandFrom = Alignment.Top) { 0 },
                exit = shrinkVertically(shrinkTowards = Alignment.Top) { 0 } ) {

                LaunchFilters(
                    selectedStartFilter = viewModel.startFilter.value,
                    onStartFilterSelected = {
                        viewModel.changeLaunchedFilter(it)
                    },
                    from = viewModel.dateFrom.value,
                    to = viewModel.dateTo.value,
                    onDatesChange = { from, to ->
                        viewModel.setDateInterval(from, to)
                    }
                )
            }

            LaunchesList(launches = launches)

            // handle loading state
            launches.apply {
                if(loadState.refresh is LoadState.Loading
                    || loadState.append is LoadState.Loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }

}

@Composable
fun LaunchesList(launches: LazyPagingItems<Launch>){
    LazyColumn(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()) {
        items(launches.itemCount){ index ->
            launches[index]?.let {
                LaunchPreview(launch = it)
            }
        }
    }
}

@Composable
fun LaunchPreview(launch: Launch){

    Box(modifier = Modifier
        .padding(top = 15.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .wrapContentHeight()
        .heightIn(min = 150.dp)
        .background(MaterialTheme.colorScheme.primaryContainer))
    {

        Row(Modifier.fillMaxHeight())
        {
            // Info about the launch
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(0.45F)
                    .fillMaxHeight()
            ) {
                Box(Modifier.fillMaxSize()){
                    Column(Modifier.align(Alignment.CenterStart)) {
                        LaunchInfo(launch = launch)
                    }
                }
            }


            // Image of the launch
            val image = launch.links?.patchImages?.small
            if(image != null){
                AsyncImage(model = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxHeight()
                )
            }
        }

        // Upcoming flag on preview
        launch.upcoming?.let {
            if(it){
                Box( Modifier.align(Alignment.TopEnd)) {
                    Upcoming()
                }
            }
        }
    }
}

// Upcoming flag
@Composable
fun Upcoming(){
    Box(
        Modifier
            .padding(5.dp)
            .shadow(5.dp, shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = stringResource(id = R.string.upcoming),
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.padding(5.dp)
        )
    }
}

// Information about the launch
// name, flight number and date
@Composable
fun LaunchInfo(launch: Launch){
    launch.name?.let {
        Text(
            text = it,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
    launch.flightNumber?.let {
        Text(
            text = "flight n. $it",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(start = 15.dp))
    }

    launch.dateUnix?.let {
        val date = ofPattern("d. M y").withZone(ZoneId.systemDefault())
            .format(Instant.ofEpochSecond(it.toLong()))
        Text(
            text = date,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(start = 15.dp))
    }
}

// Radiobutton group for selecting upcoming/past launches
// Date interval pickers
@Composable
fun LaunchFilters(selectedStartFilter: LaunchStartFilter,
                  onStartFilterSelected: (LaunchStartFilter) -> Unit,
                  from: Calendar,
                  to: Calendar,
                  onDatesChange: (Calendar, Calendar) -> Unit
){
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth()) {

            RadiobuttonGroup(
                radioOptions = LaunchStartFilter.values().map { stringResource(id = it.titleId) },
                selectedOption = LaunchStartFilter.values().indexOf(selectedStartFilter),
                onOptionSelected = { index ->
                    onStartFilterSelected(LaunchStartFilter.values()[index])
                },
                colors = RadioButtonGroupDefaults.colors(
                    labelColor = MaterialTheme.colorScheme.onPrimary,
                    groupBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedButtonColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedButtonColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(Modifier.height(10.dp))
            FromAndToDateFilter(from = from, to = to, onDatesChange = {from, to -> onDatesChange(from, to)})
        }
    }
}

// A column with pickers for choosing date interval
@Composable
fun FromAndToDateFilter(from: Calendar, to: Calendar, onDatesChange: (Calendar, Calendar) -> Unit){
    Column(
        Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
            .fillMaxWidth()){
        DateSelect(date = from, label = stringResource(R.string.from), maxDate = to){
                newFrom -> onDatesChange(newFrom, to)
        }
        DateSelect(date = to, label = stringResource(R.string.to), minDate = from){
                newTo -> onDatesChange(from, newTo)
        }
    }
}

@Composable
fun DateSelect(date: Calendar,
               label: String,
               maxDate: Calendar? = null,
               minDate: Calendar? = null,
               onDateChange: (Calendar) -> Unit ){
    Row(
        Modifier
            .height(50.dp)
            .padding(horizontal = 10.dp)) {
        // Label
        Box(Modifier.fillMaxHeight()){
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .align(Alignment.CenterStart)
                    .width(50.dp)
            )
        }
        // Button for opening a date picker
        DatePicker(date, maxDate = maxDate, minDate = minDate){
            onDateChange(it)
        }
    }
}

@Composable
fun DatePicker(date: Calendar, minDate : Calendar? = null, maxDate : Calendar? = null, onChange: (Calendar) -> Unit){
    val mContext = LocalContext.current

   val year = date.get(Calendar.YEAR)
   val month = date.get(Calendar.MONTH)
   val day = date.get(Calendar.DAY_OF_MONTH)

    // create dialog
   val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, dayOfMonth: Int ->
            date.set(Calendar.YEAR, mYear)
            date.set(Calendar.MONTH, mMonth)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            onChange(date)
        }, year, month, day
    )

    // set min and max date
    minDate?.let{
        mDatePickerDialog.datePicker.minDate = it.timeInMillis
    }
    maxDate?.let{
        mDatePickerDialog.datePicker.maxDate = it.timeInMillis
    }

    // button for showing the dialog
    Button(onClick = {
        mDatePickerDialog.show()
    },
    colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Text(text = "$day. $month $year", color = MaterialTheme.colorScheme.primary)
    }
}