package com.example.space_x.data.local.dataStores

import android.content.Context
import androidx.datastore.dataStore
import com.example.space_x.domain.company.Company
import kotlinx.coroutines.flow.Flow

class CompanyDataStore(private val appContext: Context) {

    private val Context.dataStore by dataStore("company.json", CompanySerializer)

    fun getCompany(): Flow<Company> {
        return  appContext.dataStore.data
    }

    suspend fun setCompany(company: Company){
        appContext.dataStore.updateData {
            company
        }
    }

}