package com.electionapp.android.ui.main.fragments.statfulldetails


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.base.BaseMVVMFragment
import com.electionapp.android.utils.dpToPx
import com.electionapp.android.views.decorators.SpacingItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stats_details_full.*
import javax.inject.Inject


class StatFullDetailsFragment : BaseMVVMFragment<PVCStatsFullViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_stats_details_full

    @Inject
    lateinit var adapter: SingleLayoutAdapter<StatItem>

    @Inject
    override fun injectViewModel(viewModel: PVCStatsFullViewModel) {
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

        val mode = arguments!!.getInt(MODE)

        getViewModel().setMode(mode)
        stat_rv.adapter = adapter
        stat_rv.addItemDecoration(SpacingItemDecoration(context!!.dpToPx(16), context!!.dpToPx(16), false))

    }


    companion object {

        val LGA_ = 2
        val WARD_ = 3

        val MODE = "MODE"

        @JvmStatic
        fun newInstance(mode: Int): StatFullDetailsFragment {
            val arguments = Bundle()
            arguments.putInt(MODE, mode)
            val fragment = StatFullDetailsFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

}
