package com.tonyecoleelection.android.ui.main.fragments.statfulldetails


import android.os.Bundle
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.model.admin.StatItem
import com.tonyecoleelection.android.ui.adapters.base.BindableItemClickListener
import com.tonyecoleelection.android.ui.adapters.base.SingleLayoutAdapter
import com.tonyecoleelection.android.ui.base.BaseMVVMFragment
import com.tonyecoleelection.android.ui.main.IMainFragmentNavigation
import com.tonyecoleelection.android.utils.dpToPx
import com.tonyecoleelection.android.views.decorators.SpacingItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stats_details_full.*
import javax.inject.Inject


class StatFullDetailsFragment : BaseMVVMFragment<PVCStatsFullViewModel>(), BindableItemClickListener<StatItem> {

    override fun onItemClicked(data: StatItem) {
        navigation.goToVoterDataList(data.name, mode)
    }

    override val layoutResID: Int
        get() = R.layout.fragment_stats_details_full

    var mode = 0

    @Inject
    lateinit var navigation: IMainFragmentNavigation

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

        mode = arguments!!.getInt(MODE)

        getViewModel().setMode(mode)
        stat_rv.adapter = adapter
        stat_rv.addItemDecoration(SpacingItemDecoration(context!!.dpToPx(16), context!!.dpToPx(16), false))


        swipe_to_refresh.setOnRefreshListener {
            getViewModel().setMode(mode)
        }

    }


    override fun hideLoading() {
        super.hideLoading()
        swipe_to_refresh.isRefreshing = false
    }


    companion object {

        val LGA_ = 2
        val WARD_ = 3

        val MODE = "MODE"
        val NAME = "NAME"

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
