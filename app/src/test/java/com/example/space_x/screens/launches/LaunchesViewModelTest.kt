package com.example.space_x.screens.launches

import com.example.space_x.repositories.FakeLaunchesRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.util.*

class LaunchesViewModelTest{

    lateinit var viewModel: LaunchesViewModel

    @Before
    fun init(){
        viewModel = LaunchesViewModel(FakeLaunchesRepository())
    }

    @Test
    fun `set From after To`(){
        val from = Calendar.getInstance()
        from.set(Calendar.YEAR, 2023)
        val to = Calendar.getInstance()
        to.set(Calendar.YEAR, 2012)

        assertThrows(java.lang.IllegalArgumentException::class.java) {
            viewModel.setDateInterval(from, to)
        }
    }

    @Test
    fun `set right dates`(){
        val from = Calendar.getInstance()
        from.set(Calendar.YEAR, 2020)
        val to = Calendar.getInstance()
        to.set(Calendar.YEAR, 2022)

        viewModel.setDateInterval(from, to)

        assertThat(viewModel.dateFrom.value).isEqualTo(from)
        assertThat(viewModel.dateTo.value).isEqualTo(to)
    }

    @Test
    fun `set filters`(){
        val filters = LaunchStartFilter.values()
        filters.forEach { filter ->
            viewModel.changeLaunchedFilter(filter)
            assertThat(viewModel.startFilter.value).isEqualTo(filter)
        }
    }
}