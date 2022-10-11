package com.example.space_x.screens.rocketDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space_x.repositories.RocketRepository
import com.example.space_x.repositories.SettingsRepository
import com.example.space_x.domain.rocket.Rocket
import com.example.space_x.others.UnitSystem
import com.example.space_x.others.composable.LoadingState
import com.example.space_x.repositories.RocketRepositoryI
import com.example.space_x.repositories.SettingsRepositoryI
import kotlinx.coroutines.launch

class RocketDetailViewModel(private val rocketId: String?, private val rocketRepository: RocketRepositoryI, private val settingsRepository: SettingsRepositoryI) : ViewModel(){
    private val _rocket: MutableLiveData<LoadingState<Rocket>> = MutableLiveData(LoadingState())
    val rocket: LiveData<LoadingState<Rocket>> = _rocket

    val unitSystem: UnitSystem
            get() = settingsRepository.getUnitSystem()

    init {
        viewModelScope.launch{
            // loads data and handles exceptions
            try {
                _rocket.value = _rocket.value?.copy(isLoading = true)
                _rocket.value = if(rocketId != null){
                    rocket.value?.copy(value = rocketRepository.getRocketById(rocketId))
                }else{
                    _rocket.value?.copy(showError = true)
                }
            }catch (t: Throwable){
                _rocket.value = _rocket.value?.copy(showError = true)
            }finally {
                _rocket.value = _rocket.value?.copy(isLoading = false)
            }
        }
    }
}