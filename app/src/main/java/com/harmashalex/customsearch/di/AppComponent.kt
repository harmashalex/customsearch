package com.harmashalex.customsearch.di

import android.app.Application
import com.harmashalex.customsearch.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: SearchFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}