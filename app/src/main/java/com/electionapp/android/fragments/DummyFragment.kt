package com.electionapp.android.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.electionapp.android.R


class DummyFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dummy, container, false)
    }


    companion object {

        @JvmStatic
        fun newInstance() =
                DummyFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
