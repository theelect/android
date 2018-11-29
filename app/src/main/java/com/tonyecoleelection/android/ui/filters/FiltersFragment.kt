package com.tonyecoleelection.android.ui.filters


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.RadioButton
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.adapters.filters.FiltersAdapter
import com.tonyecoleelection.android.ui.base.BaseMVVMFragment
import com.tonyecoleelection.android.ui.filters.FiltersActivity.Companion.FILTER_TASK_DATA
import com.tonyecoleelection.android.utils.appCompatActivity
import com.tonyecoleelection.android.utils.common.NotNullObserver
import com.tonyecoleelection.android.utils.dpToPx
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_filters.*
import org.jetbrains.anko.textColor
import javax.inject.Inject


class FiltersFragment : BaseMVVMFragment<FiltersViewModel>() {


    @Inject
    lateinit var filtersAdapter: FiltersAdapter


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


        initToolbar(toolbar, "Filters")

        val manager = LinearLayoutManager(context!!)
        expandable_layout.layoutManager = manager
        filtersAdapter.shouldShowHeadersForEmptySections(true)
        filtersAdapter.shouldShowFooters(false)
        expandable_layout.adapter = filtersAdapter

        val titles = listOf("LGA", "Profession")
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
            initRadioButtons(it)
        })

        clear_filter_button.setOnClickListener {
            getViewModel().clearFilters()
            setResult()
        }

        filter_button.setOnClickListener {
            setResult()
        }
    }

    private fun setResult() {
        val data = Intent()
        data.putExtra(FILTER_TASK_DATA, getViewModel().getParams().getParameters())
        activity?.setResult(RESULT_OK, data)
        activity?.finish()
    }

    private fun initRadioButtons(list: List<String>) {
        list.forEachIndexed { index, s ->
            val radioButton = RadioButton(context)
            radioButton.textColor = ContextCompat.getColor(context!!, R.color.colorAccent)
            radioButton.highlightColor = ContextCompat.getColor(context!!, R.color.colorAccent)
            radioButton.setPadding(context!!.dpToPx(16), context!!.dpToPx(8), context!!.dpToPx(16), context!!.dpToPx(8))
            radioButton.text = s
            radioButton.id = index
            professions.addView(radioButton)
        }

        //set listener to radio button group
        professions.setOnCheckedChangeListener { _, _ ->
            val checkedRadioButtonId = professions.checkedRadioButtonId
            val radioBtn = professions.findViewById(checkedRadioButtonId) as RadioButton
            getViewModel().setSelectedOccupation(radioBtn.text.toString())
        }

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
