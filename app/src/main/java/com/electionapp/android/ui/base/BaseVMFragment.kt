package com.electionapp.android.ui.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.electionapp.android.ui.main.MainActivity
import com.electionapp.android.utils.hideLoading
import com.electionapp.android.utils.hideViewLoading
import com.electionapp.android.utils.showLoading
import com.electionapp.android.utils.showViewLoading
import com.electionapp.constants.Status

/**
 * Created by f22labs on 07/03/17.
 */

abstract class BaseVMFragment<V : BaseViewModel> : Fragment() {

    private lateinit var viewModel: V


    open fun getViewModel(): V {
        return viewModel
    }

    open fun injectViewModel(viewModel: V) {
        this.viewModel = viewModel
    }

    open fun updateTitle(title: String) {
        (activity as MainActivity).updateToolbarTitle(title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.setUp()

        getViewModel().isLoading.observe(this, Observer { data ->
            if (data!!.status == Status.RUNNING) {
                activity?.showLoading()
            } else {
                activity?.hideLoading()
            }
        })

        viewModel.snackbarMessage.observe(this, Observer {
            it.let {
                Toast.makeText(context, it!!, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.snackbarStringMessage.observe(this, Observer {
            it.let {
                Toast.makeText(context, it!!.replace("java.lang.Throwable:", ""), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.dialogMessage.observe(this, Observer {
            showDialog(it!!)
        })

    }

    private fun showDialog(it: String) {
        val builder = AlertDialog.Builder(context!!)
        builder.setMessage(it)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }


    open fun showLoading() {
        showViewLoading()
    }

    open fun hideLoading() {
        hideViewLoading()
    }

    fun showDialog(title: String, action: () -> Unit) {
        val builder1 = android.app.AlertDialog.Builder(context)
        builder1.setMessage(title)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
                "Yes"
        ) { dialog, id ->
            action.invoke()
            dialog.cancel()
        }

        builder1.setNegativeButton(
                "No"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    abstract val layoutResID: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResID, container, false)
    }



    open fun injectDependencies() {

    }

}
