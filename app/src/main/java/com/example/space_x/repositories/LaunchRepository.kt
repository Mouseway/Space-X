package com.example.space_x.repositories

import androidx.paging.PagingSource
import com.example.space_x.data.LaunchPagingSource
import com.example.space_x.data.remote.LaunchHTTPBodyBuilder
import com.example.space_x.data.remote.dataSources.LaunchRemoteDataSource
import com.example.space_x.domain.launch.Launch
import com.example.space_x.screens.launches.LaunchStartFilter
import java.util.*

class LaunchRepository(private val dataSource: LaunchRemoteDataSource) : LaunchRepositoryI{

    private val query = LaunchHTTPBodyBuilder()

    private var launchPagingSource = LaunchPagingSource(query, dataSource)


    override fun setLaunchedFilter(filter: LaunchStartFilter){
        when(filter){
            LaunchStartFilter.ALL ->
                query.removeUpcoming().removePast()
            LaunchStartFilter.UPCOMING ->
                query.setUpcoming(true).removePast()
            LaunchStartFilter.PAST ->
                query.setPast(true).removeUpcoming()
        }

        launchPagingSource.invalidate()
    }

    override fun setDatesInterval(from: Calendar, to: Calendar){
        query.setDatesInterval(from.timeInMillis/1000, to.timeInMillis/1000)
        launchPagingSource.invalidate()
    }

    override fun refreshPager(): LaunchPagingSource {
        launchPagingSource = LaunchPagingSource(query, dataSource)
        return launchPagingSource
    }
}