package com.tonyecoleelection.android.ui.main.fragments.voterdata


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.model.pvc.PVCData
import com.tonyecoleelection.android.ui.adapters.base.SingleLayoutAdapter
import com.tonyecoleelection.android.ui.base.BaseMVVMFragment
import com.tonyecoleelection.android.ui.filters.FiltersActivity
import com.tonyecoleelection.android.utils.dpToPx
import com.tonyecoleelection.android.views.decorators.SpacingItemDecoration
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.filter -> {
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
        val layoutManager = LinearLayoutManager(context)
        voters_rv.addItemDecoration(SpacingItemDecoration(context!!.dpToPx(16), context!!.dpToPx(16), false))
        voters_rv.layoutManager = layoutManager
        voters_rv.adapter = adapter


        var name: String? = arguments?.getString(NAME)
        var mode: Int? = arguments?.getInt(MODE)

        if (name != null && mode != null) {
            getViewModel().setNameAndMode(name, mode)
        } else {
            getViewModel().runQuery()
        }

        voters_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !getViewModel().fetchingMore()) {
                   // getViewModel().getMore()
                }

            }
        })


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

        val MODE = "MODE"
        val NAME = "NAME"

        @JvmStatic
        fun newInstance(mode: Int, name: String): VoterDataFragment {
            val arguments = Bundle()
            arguments.putInt(MODE, mode)
            arguments.putString(NAME, name)
            val fragment = VoterDataFragment()
            fragment.arguments = arguments
            return fragment
        }

        @JvmStatic
        fun newInstance() =
                VoterDataFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
