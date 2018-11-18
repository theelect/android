package com.electionapp.android.ui.auth.fragments.signup.emailsignup


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.email_sign_up.*
import javax.inject.Inject


class EmailSignUpFragment : BaseAuthFragment<EmailSignUpViewModel>() {

    override val layoutResID: Int
        get() = R.layout.email_sign_up

    @Inject
    override fun injectViewModel(viewModel: EmailSignUpViewModel) {
        super.injectViewModel(viewModel)
    }

    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_up_button.setOnClickListener {

        }

    }

    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                EmailSignUpFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
