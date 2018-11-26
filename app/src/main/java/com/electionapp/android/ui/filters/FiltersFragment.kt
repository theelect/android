package com.electionapp.android.ui.filters


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseMVVMFragment
import com.electionapp.android.utils.appCompatActivity
import dagger.android.support.AndroidSupportInjection
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

        tabs.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                view_flipper.displayedChild = p0!!.position
            }
        })


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
