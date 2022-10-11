package com.example.space_x.screens.launches

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.space_x.R
import com.example.space_x.repositories.LaunchRepository
import com.example.space_x.domain.launch.Launch
import com.example.space_x.repositories.LaunchRepositoryI
import kotlinx.coroutines.flow.Flow
import java.util.*

class LaunchesViewModel(private val launchRepository: LaunchRepositoryI) : ViewModel() {

    private val _dateFrom: MutableState<Calendar> = mutableStateOf(Calendar.getInstance())
    val dateFrom: State<Calendar>
        get() = _dateFrom

    private val _dateTo: MutableState<Calendar> = mutableStateOf(Calendar.getInstance())
    val dateTo: State<Calendar>
        get() = _dateTo

    private val _startFilter: MutableState<LaunchStartFilter> = mutableStateOf(LaunchStartFilter.ALL)
    val startFilter: State<LaunchStartFilter>
        get() = _startFilter


    private var pager = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { launchRepository.refreshPager() },
        initialKey = 0,
    )

    val launches: Flow<PagingData<Launch>> = pager.flow.cachedIn(viewModelScope)


    init {
        // Set default date interval
        _dateFrom.value.timeInMillis = 1016060400 * 1000L // Company foundation
        _dateTo.value.timeInMillis = 1735686000 * 1000L //Year 2025
        launchRepository.setDatesInterval(dateFrom.value, dateTo.value)
    }

    fun changeLaunchedFilter(filter: LaunchStartFilter){
        _startFilter.value = filter
        launchRepository.setLaunchedFilter(filter)
    }

    fun setDateInterval(from: Calendar, to: Calendar){
        if(from.timeInMillis <= to.timeInMillis){
            _dateFrom.value = from
            _dateTo.value = to
            launchRepository.setDatesInterval(from , to)
        }else
            throw IllegalArgumentException("Date From ${from.time} can't be after date To ${to.time}")
    }
}
enum class LaunchStartFilter(val titleId: Int) {
    ALL(R.string.all),
    UPCOMING(R.string.upcoming),
    PAST(R.string.past);
}