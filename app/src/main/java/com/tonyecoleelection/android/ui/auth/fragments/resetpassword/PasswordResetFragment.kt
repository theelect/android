package com.tonyecoleelection.android.ui.auth.fragments.resetpassword


import android.os.Bundle
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_password_reset.*
import javax.inject.Inject


class PasswordResetFragment : BaseAuthFragment<PasswordResetViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_password_reset

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }

    @Inject
    override fun injectViewModel(viewModel: PasswordResetViewModel) {
        super.injectViewModel(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_button.setOnClickListener {
            getViewModel().resetPassword(email_edit_text.text.toString())
        }

    }

    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                PasswordResetFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
