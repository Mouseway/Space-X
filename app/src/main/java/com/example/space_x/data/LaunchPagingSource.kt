package com.example.space_x.data

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.space_x.data.remote.LaunchHTTPBodyBuilder
import com.example.space_x.data.remote.dataSources.LaunchRemoteDataSource
import com.example.space_x.domain.launch.Launch
import retrofit2.HttpException
import java.io.IOException

class LaunchPagingSource(private val queryBuilder: LaunchHTTPBodyBuilder, private val dataSource: LaunchRemoteDataSource) : PagingSource<Int, Launch>(){

    private var firstUsage = true

    override fun getRefreshKey(state: PagingState<Int, Launch>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        return anchorPosition / PAGE_SIZE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        return try {
            // When the filtering of launches has changed, the page is set to 1
            if(firstUsage){
                queryBuilder.setPage(1)
                firstUsage = false
            }else{
                val nextPage = params.key ?: 1
                queryBuilder.setPage(nextPage)
            }

            val response = dataSource.getAllByQuery(queryBuilder.build())

            LoadResult.Page(
                data = response.data,
                prevKey = response.prevPage,
                nextKey = if (response.hasNextPage == true) response.nextPage else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}