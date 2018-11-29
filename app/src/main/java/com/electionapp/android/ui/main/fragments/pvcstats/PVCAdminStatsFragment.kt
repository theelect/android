package com.electionapp.android.ui.main.fragments.pvcstats


import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.admin.StatGroup
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.adapters.admin.StatAdapter
import com.electionapp.android.ui.base.BaseMVVMFragment
import com.electionapp.android.ui.main.IMainFragmentNavigation
import com.electionapp.android.ui.main.MainFragmentNavigation
import com.electionapp.android.utils.common.NotNullObserver
import com.electionapp.android.utils.dpToPx
import com.electionapp.android.views.decorators.SpacingItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pvc_admin_dash.*
import javax.inject.Inject


class PVCAdminStatsFragment : BaseMVVMFragment<PVCAdminStatsViewModel>(), StatAdapter.MoreBtnClickListener {

    override val layoutResID: Int
        get() = R.layout.fragment_pvc_admin_dash

    @Inject
    lateinit var statAdapter: StatAdapter

    @Inject
    lateinit var mainFragmentNavigation: IMainFragmentNavigation

    @Inject
    override fun injectViewModel(viewModel: PVCAdminStatsViewModel) {
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

    override fun onMoreStatItemButtonClicked(mode: StatItem) {
        mainFragmentNavigation.goToVoterDataList()
    }

    override fun onMoreButtonClicked(mode: StatGroup) {
        mainFragmentNavigation.goToPVCAdminStatsDetails(mode.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stats_rv.addItemDecoration(SpacingItemDecoration(context!!.dpToPx(16), context!!.dpToPx(16), false))
        stats_rv.adapter = statAdapter

        getViewModel().totalStat.observe(this, NotNullObserver {
            statAdapter.totalStat = it
            statAdapter.notifyDataSetChanged()
        })

        getViewModel().lgaStatGroup.observe(this, NotNullObserver {
            statAdapter.lgaStatGroup = it
            statAdapter.notifyDataSetChanged()
        })

        getViewModel().wardStatGroup.observe(this, NotNullObserver {
            statAdapter.wardStatGroup = it
            statAdapter.notifyDataSetChanged()
        })

        getViewModel().genderStatGroup.observe(this, NotNullObserver {
            statAdapter.genderStatGroup = it
            statAdapter.notifyDataSetChanged()
        })

        getViewModel().professionStatGroup.observe(this, NotNullObserver {
            statAdapter.professionStatGroup = it
            statAdapter.notifyDataSetChanged()
        })


    }


    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                PVCAdminStatsFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
