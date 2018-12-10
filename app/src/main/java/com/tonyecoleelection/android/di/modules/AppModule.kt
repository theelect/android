package com.tonyecoleelection.android.di.modules

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.util.Log
import com.tonyecoleelection.android.utils.AppSchedulers
import com.tonyecoleelection.android.utils.imageloader.ImageLoader
import com.tonyecoleelection.android.utils.imageloader.PicassoImageLoader
import com.tonyecoleelection.android.utils.notifications.NotificationHelper
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.android.di.scopes.ApplicationScope
import com.tonyecoleelection.android.utils.IRestartHelper
import com.tonyecoleelection.android.utils.RestartHelper
import com.squareup.picasso.Picasso
import com.tonyecoleelection.android.di.DIConstants
import com.tonyecoleelection.constants.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideContext(application:Application): Context {
        return application
    }

    @ApplicationScope
    @Provides
    fun provideSmsManager(): SmsManager {
        return SmsManager.getDefault()
    }

    @ApplicationScope
    @Provides
    fun provideImageLoader(): ImageLoader {
        return PicassoImageLoader(Picasso.get())
    }

    @ApplicationScope
    @Provides
    fun providesAppScheduler(): Schedulers {
        return AppSchedulers()
    }

    @ApplicationScope
    @Provides
    fun providesNotificationHelper(context: Context): NotificationHelper {
        return NotificationHelper(context)
    }


    @Provides
    @ApplicationScope
    @Named("MAPS_API_KEY")
    fun providesMapsAPIKey(context: Context): String {
        var apiKey = ""
        try {
            val ai = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            val bundle = ai.metaData
            apiKey = bundle.getString("com.google.android.geo.API_KEY")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("INJECTION", "Failed to load meta-data, NameNotFound: " + e.message)
        } catch (e: NullPointerException) {
            Log.e("INJECTION", "Failed to load meta-data, NullPointer: " + e.message)
        }
        return apiKey
    }




    @ApplicationScope
    @Provides
    fun providesRestartHelper(restartHelper: RestartHelper): IRestartHelper {
        return restartHelper
    }

    @ApplicationScope
    @Provides
    @Named(DIConstants.DATA_SHARE_MODULE.SELECTED_WARDS_LIST)
    fun providesSelectedWardList(): MutableList<String> {
        return mutableListOf()
    }

    @ApplicationScope
    @Provides
    @Named(DIConstants.DATA_SHARE_MODULE.SELECTED_LGAS_LIST)
    fun providesSelectedLGAList(): MutableList<String> {
        return mutableListOf()
    }


    @ApplicationScope
    @Provides
    @Named(DIConstants.DATA_SHARE_MODULE.SELECTED_PROFESSIONS_LIST)
    fun providesSelectedProfessionsList(): MutableList<String> {
        val list = mutableListOf<String>()
       // list.add(Constants.FILTER_CONSTANTS.ALL)
        return list    }

    @ApplicationScope
    @Provides
    @Named(DIConstants.DATA_SHARE_MODULE.SELECTED_AGE_GROUPS_LIST)
    fun providesSelectedAgeGroupList(): MutableList<String> {
        val list = mutableListOf<String>()
       // list.add(Constants.FILTER_CONSTANTS.ALL)
        return list
    }

}