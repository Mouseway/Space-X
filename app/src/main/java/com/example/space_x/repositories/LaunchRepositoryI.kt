package com.example.space_x.repositories

import androidx.paging.PagingSource
import com.example.space_x.data.LaunchPagingSource
import com.example.space_x.domain.launch.Launch
import com.example.space_x.screens.launches.LaunchStartFilter
import java.util.*

interface LaunchRepositoryI {
    fun setLaunchedFilter(filter: LaunchStartFilter)
    fun setDatesInterval(from: Calendar, to: Calendar)
    fun refreshPager(): LaunchPagingSource
}