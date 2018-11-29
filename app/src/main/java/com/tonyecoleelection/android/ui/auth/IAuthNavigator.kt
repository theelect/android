package com.tonyecoleelection.android.ui.auth

import android.support.v4.app.FragmentManager
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.fragments.entry.EntryFragment
import com.tonyecoleelection.android.ui.auth.fragments.login.LoginFragment
import com.tonyecoleelection.android.ui.auth.fragments.onboarding.OnBoardingFragment
import com.tonyecoleelection.android.ui.auth.fragments.resetpassword.PasswordResetFragment
import com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup.EmailSignUpFragment
import com.tonyecoleelection.android.ui.base.BaseNavigator
import com.tonyecoleelection.android.ui.main.MainActivity
import javax.inject.Inject

interface IAuthNavigator {

    fun goToLogin()

    fun goToSignUp()

    fun goToOnBoarding()

    fun goToPasswordReset()

    fun goToMain()

    fun goBack()

    fun goToEntry()
}

class AuthNavigator @Inject constructor(val authActivity: AuthActivity) : IAuthNavigator, BaseNavigator() {
    override fun goToEntry() {
        var fragment = findFragmentInstance(EntryFragment.TAG)
        if (fragment == null) {
            fragment = EntryFragment.newInstance()
        }
        addFragmentWithBackStack(fragment)
    }


    override fun goBack() {
        val stackSize = getFragmentManager()!!.backStackEntryCount
        if (stackSize > 1) {
            getFragmentManager()?.popBackStack()
        } else {
            authActivity.onBackPressed()
        }
    }


    override fun goToSignUp() {
        var fragment = findFragmentInstance(EmailSignUpFragment.TAG)
        if (fragment == null) {
            fragment = EmailSignUpFragment.newInstance()
        }
        addFragmentWithBackStack(fragment)
    }

    override fun goToOnBoarding() {
        var fragment = findFragmentInstance(OnBoardingFragment.TAG)
        if (fragment == null) {
            fragment = OnBoardingFragment.newInstance()
        }
        replaceFragment(fragment)
    }

    override fun goToPasswordReset() {
        var fragment = findFragmentInstance(PasswordResetFragment.TAG)
        if (fragment == null) {
            fragment = PasswordResetFragment.newInstance()
        }
        addFragmentWithBackStack(fragment)
    }


    override fun getFragmentManager(): FragmentManager? {
        return authActivity.supportFragmentManager
    }

    override fun getLayoutID(): Int {
        return R.id.content_frame
    }

    override fun goToMain() {
        MainActivity.start(context = authActivity)
        authActivity.finish()
    }

    override fun goToLogin() {
        var fragment = findFragmentInstance(LoginFragment.TAG)
        if (fragment == null) {
            fragment = LoginFragment.newInstance()
        }
        addFragmentWithBackStack(fragment)
    }


}
