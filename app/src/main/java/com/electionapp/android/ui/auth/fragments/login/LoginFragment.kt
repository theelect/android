package com.electionapp.android.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.facebook.CallbackManager
import com.electionapp.android.R
import com.electionapp.android.ui.auth.fragments.BaseAuthFragment
import com.electionapp.data.contracts.IGoogleLoginManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : BaseAuthFragment<LoginViewModel>() {


    override val layoutResID: Int
        get() = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @Inject
    override fun injectViewModel(viewModel: LoginViewModel) {
        super.injectViewModel(viewModel)
    }


    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forgot_password_btn.setOnClickListener {
            getViewModel().goToPasswordReset()
        }

        login_button.setOnClickListener {
            getViewModel().logUserIn(email_edit_text.text.toString(), password_edit_text.text.toString())
        }


    }


    companion object {

        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                LoginFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
