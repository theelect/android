package com.electionapp.android.ui.main.fragments.voterdata


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.base.BaseMVVMFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_voter_data.*
import javax.inject.Inject


class VoterDataFragment : BaseMVVMFragment<VoterDataViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_voter_data

    @Inject
    lateinit var adapter: SingleLayoutAdapter<PVCData>

    @Inject
    override fun injectViewModel(viewModel: VoterDataViewModel) {
        super.injectViewModel(viewModel)
    }

    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        voters_rv.adapter = adapter

    }

    override fun showLoading() {
        super.showLoading()
        swipe_to_refresh_stats.isRefreshing = true
    }

    override fun hideLoading() {
        super.hideLoading()
        swipe_to_refresh_stats.isRefreshing = false
    }


    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                VoterDataFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
