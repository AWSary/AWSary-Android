package com.lzcalderaro.awsary

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.lzcalderaro.awsary.repository.AwsServicesRepositoryImp
import com.lzcalderaro.awsary.viewModels.HomeScreenViewModel
import com.lzcalderaro.awsary.viewModels.DetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val module = module {
    singleOf(::AwsServicesRepositoryImp)
    viewModel{ HomeScreenViewModel(get()) }
    viewModel{ DetailScreenViewModel(get()) }
}
