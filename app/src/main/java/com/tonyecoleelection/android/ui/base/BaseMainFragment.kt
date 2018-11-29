package com.tonyecoleelection.android.ui.base

import com.tonyecoleelection.android.ui.main.IMainFragmentNavigation
import javax.inject.Inject

/**
 * Created by f22labs on 07/03/17.
 */

abstract class BaseMainFragment<V : BaseViewModel> : BaseVMFragment<V>() {

    @Inject
    lateinit var fragmentNavigation: IMainFragmentNavigation
//
//    @Inject
//    lateinit var imageLoader: ImageLoader
//
//    @Inject
//    lateinit var menuManager: IMenuManager
//
//    var mainComponent: MainActivityComponent? = null
//
//    /**
//     * Why?
//     *
//     * https://medium.com/androiddevelopers/the-android-lifecycle-cheat-sheet-part-iii-fragments-afc87d4f37fd
//     *
//     * that's why :)
//     *
//     * **/
//
//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        initMainComponent()
//    }
//
//
//    private fun initMainComponent() {
//        if (context is MainActivity) {
//            mainComponent = (context as MainActivity).mainComponent
//        }
//    }


}
