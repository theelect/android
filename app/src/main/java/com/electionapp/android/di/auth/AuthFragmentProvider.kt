package com.electionapp.android.di.auth

import com.electionapp.android.di.auth.emailsignup.EmailSignUpModule
import com.electionapp.android.di.auth.login.LoginModule
import com.electionapp.android.di.auth.onboarding.OnBoardingModule
import com.electionapp.android.di.auth.passwordreset.PasswordResetModule
import com.electionapp.android.ui.auth.fragments.login.LoginFragment
import com.electionapp.android.ui.auth.fragments.onboarding.OnBoardingFragment
import com.electionapp.android.ui.auth.fragments.resetpassword.PasswordResetFragment
import com.electionapp.android.ui.auth.fragments.signup.emailsignup.EmailSignUpFragment
import com.electionapp.android.di.scopes.FragmentScope
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
    @ContributesAndroidInjector(modules = [EmailSignUpModule::class])
    internal abstract fun bindEmailSignUpFragment(): EmailSignUpFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PasswordResetModule::class])
    internal abstract fun bindPasswordResetFragment(): PasswordResetFragment

}