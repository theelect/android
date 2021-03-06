package com.tonyecoleelection.android.ui.auth.fragments.onboarding


import android.os.Bundle
import android.view.View
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.fragments.BaseAuthFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_onboarding.*
import java.util.*
import javax.inject.Inject


class OnBoardingFragment : BaseAuthFragment<OnBoardingViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_onboarding

    val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @Inject
    override fun injectViewModel(viewModel: OnBoardingViewModel) {
        super.injectViewModel(viewModel)
    }


    override fun injectDependencies() {
        super.injectDependencies()
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continue_button.setOnClickListener {
            getViewModel().goToEntry()
        }

    }


    companion object {
        val TAG = this::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
                OnBoardingFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
