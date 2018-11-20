package com.electionapp.android.di

import android.app.Application
import com.electionapp.domain.base.Schedulers
import com.electionapp.android.ApplicationClass
import com.electionapp.android.di.modules.*
import com.electionapp.android.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Created by aliumujib on 14/05/2018.
 **/

@ApplicationScope
@Component(modules = [
    (ActivityBuilder::class),
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (DomainMapperModule::class),
    (PresentationMapperModule::class),
    (NetworkModule::class),
    (RepositoryModule::class)])

interface AppComponent  {

    fun inject(app: ApplicationClass)

    fun schedulers(): Schedulers

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }


}