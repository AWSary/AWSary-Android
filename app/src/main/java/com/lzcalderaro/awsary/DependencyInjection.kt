package com.lzcalderaro.awsary

import android.app.Activity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val module = module {
    /*singleOf(::PackageRepository)
    viewModel { PackageViewModel(get()) }
    viewModel { DownloadViewModel(get()) }

    factory { (mActivity: Activity) -> Package(mActivity) }*/
}
