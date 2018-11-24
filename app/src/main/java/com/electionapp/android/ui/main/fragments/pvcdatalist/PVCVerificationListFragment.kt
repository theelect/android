package com.electionapp.android.ui.main.fragments.pvcdatalist


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.base.BaseMVVMFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pvc_verifcation.*
import kotlinx.android.synthetic.main.fragment_pvc_verifcation_list.*
import javax.inject.Inject


class PVCVerificationListFragment : BaseMVVMFragment<PVCVerificationListViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_pvc_verifcation_list


    @Inject
    lateinit var singleLayoutAdapter: SingleLayoutAdapter<PVCData>

    @Inject
    override fun injectViewModel(viewModel: PVCVerificationListViewModel) {
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

        listings_rv.adapter = singleLayoutAdapter

    }


    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                PVCVerificationListFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
