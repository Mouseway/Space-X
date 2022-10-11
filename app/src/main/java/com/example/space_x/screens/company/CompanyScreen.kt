package com.example.space_x.screens.company

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.space_x.R
import com.example.space_x.domain.company.Company
import com.example.space_x.domain.company.Headquarters
import com.example.space_x.screens.SharedViewModel
import com.example.space_x.others.*
import com.example.space_x.others.composable.LoadingWrapper
import com.example.space_x.others.composable.LongText
import com.example.space_x.others.composable.Subtitle
import com.example.space_x.others.composable.Title
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyScreen(sharedViewModel: SharedViewModel) {

    val viewModel by viewModel<CompanyViewModel>()

    val companyState = viewModel.company.observeAsState()

    Scaffold(topBar ={
            AppBar(
              title = stringResource(R.string.space_x),
              sharedViewModel.drawerState,
              options = sharedViewModel.topBarOptions
            )
        }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())){
            if(companyState.value != null){
                LoadingWrapper(loadingState = companyState.value!!) { company ->
                    CompanyDetail(company = company)
                }
            }
        }
    }


}

@Composable
fun CompanyDetail(company: Company){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        company.name?.let {
            Title(title = it)
        }
        company.summary?.let {
            LongText(text = it)
        }

        AboutCompanySection(company = company)
        company.headquarters?.let { HeadquartersSection(headquarters = it) }
    }
}
@Composable
fun AboutCompanySection(company: Company){
    Column(modifier = Modifier
        .padding(top = 20.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Subtitle(title = stringResource(id = R.string.about_company_title))
        company.founder?.let { InfoProperty(label = stringResource(R.string.founder), value = it) }
        company.founded?.let { InfoProperty(label = stringResource(R.string.founded), value = it.toString()) }
        company.cto?.let { InfoProperty(label = stringResource(R.string.CTO), value = it) }
        company.ceo?.let { InfoProperty(label = stringResource(R.string.CEO), value = it) }
        company.coo?.let { InfoProperty(label = stringResource(R.string.COO), value = it) }
        company.ctoPropulsion?.let { InfoProperty(label = stringResource(R.string.CTO_propulsion), value = it) }
        company.employees?.let {
            InfoProperty(
                label = stringResource(R.string.current_employees),
                value = it.toString()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun HeadquartersSection(headquarters: Headquarters){
    Column(modifier = Modifier.padding(top = 20.dp)) {
        Subtitle(title = stringResource(R.string.headquarters))
        headquarters.address?.let { InfoProperty(label = stringResource(R.string.address), value = it) }
        headquarters.city?.let { InfoProperty(label = stringResource(R.string.city), value = it) }
        headquarters.state?.let { InfoProperty(label = stringResource(R.string.state), value = it) }
    }
}