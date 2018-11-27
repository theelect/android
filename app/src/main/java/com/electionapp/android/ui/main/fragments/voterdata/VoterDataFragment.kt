package com.electionapp.android.ui.main.fragments.voterdata


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.base.BaseMVVMFragment
import com.electionapp.android.utils.dpToPx
import com.electionapp.android.views.decorators.SpacingItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_voter_data.*
import javax.inject.Inject
import android.view.MenuInflater
import android.view.MenuItem
import com.electionapp.android.ui.filters.FiltersActivity


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

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.filter ->
            {
                FiltersActivity.startForResult(this)
                // do stuff
                return true
            }
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        voters_rv.addItemDecoration(SpacingItemDecoration(context!!.dpToPx(16), context!!.dpToPx(16),false))
        voters_rv.adapter = adapter

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FiltersActivity.FILTER_TASK_REQ_CODE) {
                val hashMap: HashMap<String, Any> = data!!.getSerializableExtra(FiltersActivity.FILTER_TASK_DATA) as HashMap<String, Any>
                getViewModel().queryWithFilters(hashMap)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pvc_data_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
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
