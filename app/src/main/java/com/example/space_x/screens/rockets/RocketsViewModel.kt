package com.example.space_x.screens.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space_x.repositories.RocketRepository
import com.example.space_x.domain.rocket.Rocket
import com.example.space_x.others.composable.LoadingState
import com.example.space_x.repositories.RocketRepositoryI
import kotlinx.coroutines.launch

class RocketsViewModel(private val rocketRepository: RocketRepositoryI) : ViewModel() {
    private val _rockets: MutableLiveData<LoadingState<List<Rocket>>> = MutableLiveData(LoadingState())
    val rockets: LiveData<LoadingState<List<Rocket>>> = _rockets

    init {
        // Loads rockets and handle exceptions
        viewModelScope.launch{
            try {
                _rockets.value = _rockets.value?.copy(isLoading = true)
                _rockets.value = _rockets.value?.copy(value = rocketRepository.getAllRockets())
            }catch (t: Throwable){
                _rockets.value = _rockets.value?.copy(showError = true)
            }finally {
                _rockets.value = _rockets.value?.copy(isLoading = false)
            }
        }
    }
}