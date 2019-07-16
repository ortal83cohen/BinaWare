package com.cohen.binaware.helpers.di

import androidx.room.Room
import com.cohen.binaware.helpers.room.AppDatabase
import com.cohen.binaware.room.Persistent
import com.cohen.binaware.viewmodel.AddTicketViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    //    factory<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
//    factory<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view) }
//    factory<FeatureContract.Presenter<Student>> { (view: FeatureContract.View<Student>) -> FeaturePresenter(view) }

//    single<SharedPreferences> {
//        androidContext().getSharedPreferences(
//            "SharedPreferences",
//            Context.MODE_PRIVATE
//        )
//    }
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            AppDatabase::class.java, "app-database"
//        ).build()
//    }
//    single {
//        AppDatabase.getAppDatabase( androidContext())
//    }
    single { Persistent(androidContext()) }

}
var viewModelModule = module {
    viewModel {
        AddTicketViewModel()
    }
}
