package com.tonyecoleelection.android

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.tonyecoleelection.android.di.AppComponent
import com.tonyecoleelection.android.di.DaggerAppComponent
import com.tonyecoleelection.android.utils.binding.BindingComponentImpl
import com.tonyecoleelection.android.utils.imageloader.ImageLoader
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


/**
 * Created by aliumujib on 14/05/2018.
 */

class ApplicationClass : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    val TAG = javaClass.canonicalName

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationClass = this

        //Fabric.with(this, Crashlytics())

        Stetho.initializeWithDefaults(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        initDependencies()
    }


    private fun initDependencies() {
        appComponent = DaggerAppComponent.builder()
                //.appModule(AppModule(this))
                .application(this)
                .build()

        appComponent.inject(this)

        DataBindingUtil.setDefaultComponent(BindingComponentImpl(imageLoader))

    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
    }

}