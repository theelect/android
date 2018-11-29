package com.tonyecoleelection.android.ui.auth

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log.d
import com.tonyecoleelection.android.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject


/**
 * Created by aliumujib on.
 */

open class AuthActivity : AppCompatActivity(), HasSupportFragmentInjector {


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    @Inject
    lateinit var authNavigator: IAuthNavigator


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.auth_activity)

        try {
            val info = packageManager.getPackageInfo(
                    application.packageName,
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        authNavigator.goToEntry()

    }


    companion object {
        fun start(context: Context) {
            var intent = Intent(context, AuthActivity::class.java)
            context.startActivity(intent)
        }
    }

}
