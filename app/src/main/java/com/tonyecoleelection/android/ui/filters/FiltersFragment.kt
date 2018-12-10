package com.tonyecoleelection.android.ui.filters


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.di.DIConstants
import com.tonyecoleelection.android.ui.adapters.filters.FiltersAdapter
import com.tonyecoleelection.android.ui.adapters.filters.MultiSelectAdapter
import com.tonyecoleelection.android.ui.base.BaseMVVMFragment
import com.tonyecoleelection.android.ui.filters.FiltersActivity.Companion.FILTER_TASK_DATA
import com.tonyecoleelection.android.utils.appCompatActivity
import com.tonyecoleelection.android.utils.common.NotNullObserver
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_filters.*
import javax.inject.Inject
import javax.inject.Named


class FiltersFragment : BaseMVVMFragment<FiltersViewModel>() {


    @Inject
    lateinit var filtersAdapter: FiltersAdapter


    @Inject
    @field:[Named(DIConstants.DATA_SHARE_MODULE.PROFESSIONS_FILTER_LIST)]
    lateinit var professionsAdapter: MultiSelectAdapter

    @Inject
    @field:[Named(DIConstants.DATA_SHARE_MODULE.AGE_GROUPS_FILTER_LIST)]
    lateinit var ageGroupsAdapter: MultiSelectAdapter


    override val layoutResID: Int
        get() = R.layout.fragment_filters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    @Inject
    override fun injectViewModel(viewModel: FiltersViewModel) {
        super.injectViewModel(viewModel)
    }


    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(false)

        initToolbar(toolbar, "Filters")

        val lgaFiltersLayoutManager = LinearLayoutManager(context!!)
        expandable_layout.layoutManager = lgaFiltersLayoutManager
        filtersAdapter.shouldShowHeadersForEmptySections(true)
        filtersAdapter.shouldShowFooters(false)
        expandable_layout.adapter = filtersAdapter

        professions.adapter = professionsAdapter
        age_groups.adapter = ageGroupsAdapter

        val titles = listOf("LGA", "Profession", "Age")

        for (i in 0 until titles.size) {
            tabs.addTab(tabs.newTab().setText(titles[i]))
        }

        view_flipper.displayedChild = 0

        tabs.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                view_flipper.displayedChild = p0!!.position
            }
        })

        getViewModel().lgas.observe(this, NotNullObserver {
            filtersAdapter.addData(it)
            filtersAdapter.collapseAllSections()
        })

        getViewModel().occupations.observe(this, NotNullObserver {
            professionsAdapter.addData(it)
        })

        getViewModel().ageGroups.observe(this, NotNullObserver {
            ageGroupsAdapter.addData(it)
        })

        clear_filters_button.setOnClickListener {
            filtersAdapter.clearSelection()
            ageGroupsAdapter.clearSelection()
            professionsAdapter.clearSelection()
            getViewModel().clearFilters()
            setResult()
        }

        apply_filters_button.setOnClickListener {
            setResult()
        }
        
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.apply_filter -> {
                setResult()
                // do stuff
                return true
            }
            R.id.clear_filters -> {
                filtersAdapter.clearSelection()

                getViewModel().clearFilters()
                setResult()
                // do stuff
                return true
            }
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pvc_filters, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setResult() {
        getViewModel().setSelectedLGAs(filtersAdapter.getCheckedLGAs())
        getViewModel().setSelectedWards(filtersAdapter.getCheckedWards())
        getViewModel().setSelectedAgeGroups(ageGroupsAdapter.selectedItems)
        getViewModel().setSelectedOccupations(professionsAdapter.selectedItems)

        val data = Intent()
        data.putExtra(FILTER_TASK_DATA, getViewModel().getParams().getParameters())
        activity?.setResult(RESULT_OK, data)
        activity?.finish()
    }


    private fun initToolbar(toolbar: Toolbar, title: String) {
        appCompatActivity().setSupportActionBar(toolbar)
        appCompatActivity().supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity().supportActionBar!!.setDisplayShowHomeEnabled(true)
        appCompatActivity().supportActionBar!!.title = title
        toolbar.setNavigationOnClickListener {
            activity?.finish()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                FiltersFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

}
