package com.electionapp.android.ui.main.fragments.verifypvcdata


import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.locale.Ward
import com.electionapp.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.email_sign_up.*
import java.util.ArrayList
import javax.inject.Inject


class PVCVerificationFragment : BaseAuthFragment<PVCVerificationViewModel>() {

    override val layoutResID: Int
        get() = R.layout.pvc_verifcation_sign_up

    @Inject
    override fun injectViewModel(viewModel: PVCVerificationViewModel) {
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



    }


    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                PVCVerificationFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
