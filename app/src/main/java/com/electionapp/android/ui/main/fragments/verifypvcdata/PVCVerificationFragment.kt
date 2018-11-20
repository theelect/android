package com.electionapp.android.ui.main.fragments.verifypvcdata


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseMVVMFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pvc_verifcation.*
import javax.inject.Inject


class PVCVerificationFragment : BaseMVVMFragment<PVCVerificationViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_pvc_verifcation

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

        verify_by_app_button.setOnClickListener {
            getViewModel().verifyPVCWithDetails(first_name_edit_text.text.toString(), last_name_edit_text.text.toString(),
                    phone_edit_text.text.toString(), vin_edit_text.text.toString())

        }

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
