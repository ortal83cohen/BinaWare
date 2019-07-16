package com.cohen.binaware.helpers.di

import com.cohen.binaware.room.Persistent
import com.cohen.binaware.viewmodel.AddTicketViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    //    factory<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    single { Persistent(androidContext()) }

}
var viewModelModule = module {
    viewModel {
        AddTicketViewModel(get())
    }
}
