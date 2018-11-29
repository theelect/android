package com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup


import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.email_sign_up.*
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

        val spinnerDialog = SpinnerDialog(this.activity, getViewModel().getLGAList(), "Select or Search Ward", R.style.DialogAnimations_SmileWindow, R.drawable.ic_source_commit_start_next_local_grey600_36dp, R.drawable.ic_source_commit_start_next_local_grey600_24dp)

        spinnerDialog.bindOnSpinerListener { item, position ->
            lga_edit_text.setText(item.title)
            getViewModel().setSelectedLGA(item)
        }

        spinnerDialog.showSpinerDialog()
    }


    fun showWards() {
        if (getViewModel().getSelectedLGA() != null) {

            val spinnerDialog = SpinnerDialog(this.activity, getViewModel().getSelectedLGA()!!.wards, "Select or Search Ward", R.style.DialogAnimations_SmileWindow, R.drawable.ic_city_grey600_24dp, R.drawable.ic_city_grey600_24dp)

            spinnerDialog.bindOnSpinerListener { item, position ->
                ward_edit_text.setText(item.title)
            }

            spinnerDialog.showSpinerDialog()
        }
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
