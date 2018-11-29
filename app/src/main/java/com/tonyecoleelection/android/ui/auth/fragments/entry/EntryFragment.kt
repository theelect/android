package com.tonyecoleelection.android.ui.auth.fragments.entry

import android.os.Bundle
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_entry.*
import javax.inject.Inject


class EntryFragment : BaseAuthFragment<EntryViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_entry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @Inject
    override fun injectViewModel(viewModel: EntryViewModel) {
        super.injectViewModel(viewModel)
    }


    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_up_button.setOnClickListener {
            getViewModel().gotoSignUp()
        }

        login_button.setOnClickListener {
            getViewModel().gotoLogin()
        }
    }


    companion object {

        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                EntryFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
