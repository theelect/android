package com.electionapp.android.ui.filters


import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.electionapp.android.R
import com.electionapp.android.model.locale.LGA
import com.electionapp.android.ui.base.BaseMVVMFragment
import com.electionapp.android.ui.filters.FiltersActivity.Companion.FILTER_TASK_DATA
import com.electionapp.android.utils.appCompatActivity
import com.electionapp.android.utils.common.NotNullObserver
import dagger.android.support.AndroidSupportInjection
import iammert.com.expandablelib.ExpandableLayout
import iammert.com.expandablelib.Section
import kotlinx.android.synthetic.main.fragment_filters.*
import javax.inject.Inject


class FiltersFragment : BaseMVVMFragment<FiltersViewModel>() {

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
            initLGAWardSectionFilter(it)
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


    private fun initLGAWardSectionFilter(list: List<LGA>) {

        expandable_layout.setRenderer(object : ExpandableLayout.Renderer<LGA, IdentifiableObject> {
            override fun renderParent(view: View, model: LGA, isExpanded: Boolean, parentPosition: Int) {
                (view.findViewById<View>(R.id.tvParent) as TextView).text = model.name
                (view.findViewById<View>(R.id.tvParent) as TextView).setOnClickListener {
                    getViewModel().setSelectedLGA((it as TextView).text.toString())
                }
                view.findViewById<View>(R.id.arrow).setBackgroundResource(if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down)
            }

            override fun renderChild(view: View, model: IdentifiableObject, parentPosition: Int, childPosition: Int) {
                (view.findViewById<View>(R.id.tvChild) as TextView).text = model.title
                (view.findViewById<View>(R.id.tvChild) as TextView).setOnClickListener {
                    getViewModel().setSelectedWard((it as TextView).text.toString())
                }
            }
        })

        list.forEach {
            expandable_layout.addSection(getSectionForLGA(it))
        }

    }

    fun getSectionForLGA(lga: LGA): Section<LGA, IdentifiableObject> {
        val section = Section<LGA, IdentifiableObject>()

        section.parent = lga
        lga.wards.forEach {
            section.children.add(it)
        }
        return section
    }

    private fun initToolbar(toolbar: Toolbar, title: String) {
        appCompatActivity().setSupportActionBar(toolbar)
        appCompatActivity().supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity().supportActionBar!!.setDisplayShowHomeEnabled(true)
        appCompatActivity().supportActionBar!!.title = title
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
