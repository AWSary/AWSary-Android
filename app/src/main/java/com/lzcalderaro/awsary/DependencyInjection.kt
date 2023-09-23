package com.lzcalderaro.awsary

import android.app.Activity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.lzcalderaro.awsary.repository.AwsServicesRepository
import com.lzcalderaro.awsary.viewModels.AwsServicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val module = module {
    singleOf(::AwsServicesRepository)
    viewModel{ AwsServicesViewModel(get()) }
}
