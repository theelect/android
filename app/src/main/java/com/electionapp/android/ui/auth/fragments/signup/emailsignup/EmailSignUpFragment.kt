package com.electionapp.android.ui.auth.fragments.signup.emailsignup


import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.view.View
import com.electionapp.android.R
import com.electionapp.android.model.locale.Ward
import com.electionapp.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.email_sign_up.*
import java.util.ArrayList
import javax.inject.Inject


class EmailSignUpFragment : BaseAuthFragment<EmailSignUpViewModel>() {

    override val layoutResID: Int
        get() = R.layout.email_sign_up

    @Inject
    override fun injectViewModel(viewModel: EmailSignUpViewModel) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_up_button.setOnClickListener {
            getViewModel().signUserUp(
                    email_edit_text.text.toString(), first_name_edit_text.text.toString(), last_name_edit_text.text.toString(),
                    password_edit_text.text.toString(), phone_edit_text.text.toString(), ward_edit_text.text.toString(),
                    lga_edit_text.text.toString(),
                    vin_edit_text.text.toString())
        }

        ward_edit_text.setOnClickListener {
            showWards()
        }

        lga_edit_text.setOnClickListener {
            showLGAs()
        }

    }


    fun showLGAs() {
        val lgas = ArrayList<IdentifiableObject>()

        //TODO remvoe when I get real wards from Johnson
        for (i in 1..20) {
            lgas.add(Ward(i, "LGA $i"))
        }


        val spinnerDialog = SpinnerDialog(this.activity, lgas, "Select or Search Ward", R.style.DialogAnimations_SmileWindow, R.drawable.email_grey, R.drawable.email_grey)

        spinnerDialog.bindOnSpinerListener { item, position ->
            lga_edit_text.setText(item.title)
        }

        spinnerDialog.showSpinerDialog()
    }


    fun showWards() {
        val wards = ArrayList<IdentifiableObject>()

        //TODO remvoe when I get real wards from Johnson
        for (i in 1..20) {
            wards.add(Ward(i, "Ward $i"))
        }


        val spinnerDialog = SpinnerDialog(this.activity, wards, "Select or Search Ward", R.style.DialogAnimations_SmileWindow, R.drawable.email_grey, R.drawable.email_grey)

        spinnerDialog.bindOnSpinerListener { item, position ->
            ward_edit_text.setText(item.title + " Position: " + item.subtitle)
        }

        spinnerDialog.showSpinerDialog()
    }

    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                EmailSignUpFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
