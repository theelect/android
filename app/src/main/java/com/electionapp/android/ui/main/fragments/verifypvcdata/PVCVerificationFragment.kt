package com.electionapp.android.ui.main.fragments.verifypvcdata


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
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
