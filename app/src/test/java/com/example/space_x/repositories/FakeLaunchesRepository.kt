package com.example.space_x.repositories

import androidx.paging.PagingSource
import com.example.space_x.domain.launch.Launch
import com.example.space_x.screens.launches.LaunchStartFilter
import java.util.*

class FakeLaunchesRepository : LaunchRepositoryI {
    private var filter: LaunchStartFilter = LaunchStartFilter.ALL
    private var from: Calendar = Calendar.getInstance()
    private var to: Calendar = Calendar.getInstance()

    override fun setLaunchedFilter(filter: LaunchStartFilter) {
        this.filter = filter
    }

    override fun setDatesInterval(from: Calendar, to: Calendar) {
        this.from = from
        this.to = to
    }

    override fun refreshPager(): PagingSource<Int, Launch> {
        TODO("Not yet implemented")
    }
}