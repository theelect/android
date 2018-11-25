package com.electionapp.android.ui.filters


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.electionapp.android.R
import com.electionapp.android.fragments.BaseFragment
import com.electionapp.android.fragments.DummyFragment
import com.electionapp.android.utils.ViewPagerAdapter
import com.electionapp.android.utils.appCompatActivity
import kotlinx.android.synthetic.main.fragment_filters.*


class FiltersFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initToolbar(toolbar, "Filters")
        setupViewPager(viewpager, tabs)
    }

    fun initToolbar(toolbar: Toolbar, title: String) {

        appCompatActivity().setSupportActionBar(toolbar)
        appCompatActivity().supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity().supportActionBar!!.setDisplayShowHomeEnabled(true)
        appCompatActivity().supportActionBar!!.title = title


    }

    private fun setupViewPager(viewPager: ViewPager, tabLayout: TabLayout) {
        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFrag(DummyFragment.newInstance(), "LGA")
        adapter.addFrag(DummyFragment.newInstance(), "Profession")
        adapter.addFrag(DummyFragment.newInstance(), "Order By")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
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
