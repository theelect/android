package com.tonyecoleelection.android.di.auth

import com.tonyecoleelection.android.di.auth.emailsignup.EmailSignUpModule
import com.tonyecoleelection.android.di.auth.entry.EntryModule
import com.tonyecoleelection.android.di.auth.login.LoginModule
import com.tonyecoleelection.android.di.auth.onboarding.OnBoardingModule
import com.tonyecoleelection.android.di.auth.passwordreset.PasswordResetModule
import com.tonyecoleelection.android.ui.auth.fragments.login.LoginFragment
import com.tonyecoleelection.android.ui.auth.fragments.onboarding.OnBoardingFragment
import com.tonyecoleelection.android.ui.auth.fragments.resetpassword.PasswordResetFragment
import com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup.EmailSignUpFragment
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.auth.fragments.entry.EntryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AuthFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun bindsLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [OnBoardingModule::class])
    internal abstract fun bindOnBoardingFragment(): OnBoardingFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [EntryModule::class])
    internal abstract fun bindEntryFragment(): EntryFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [EmailSignUpModule::class])
    internal abstract fun bindEmailSignUpFragment(): EmailSignUpFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PasswordResetModule::class])
    internal abstract fun bindPasswordResetFragment(): PasswordResetFragment

}