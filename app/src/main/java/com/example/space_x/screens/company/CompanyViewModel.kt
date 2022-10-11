package com.example.space_x.screens.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space_x.repositories.CompanyRepository
import com.example.space_x.domain.company.Company
import com.example.space_x.others.composable.LoadingState
import com.example.space_x.repositories.CompanyRepositoryI
import kotlinx.coroutines.launch

class CompanyViewModel(val companyRepository: CompanyRepositoryI) : ViewModel() {

    private val _company: MutableLiveData<LoadingState<Company>> = MutableLiveData(LoadingState())
    val company: LiveData<LoadingState<Company>> = _company

    init {

        viewModelScope.launch {
            // Load company data from memory
            companyRepository.getCompany().collect{
                _company.value = _company.value?.copy(value = it)
            }
        }

        viewModelScope.launch{
            // load company data from API and handle loading state
            try {
                _company.value = _company.value?.copy(isLoading = true)
                companyRepository.fetchCompany()
            }catch (t: Throwable){
                _company.value = _company.value?.copy(showError = true)
            }finally {
                _company.value = _company.value?.copy(isLoading = false)
            }
        }
    }
}