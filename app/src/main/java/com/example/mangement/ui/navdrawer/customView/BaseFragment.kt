package com.example.mangement.ui.navdrawer.customView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mangement.ui.activity.dashboard.MainActivity
import kotlinx.android.synthetic.main.activity_test_main.*

abstract class BaseFragment : Fragment() {


    abstract fun setupHeader(headerHandler: HeaderHandler, header: CustomHeader)
    abstract fun setupFooter(bottomNavigationHandler: BottomNavigationHandler)
    lateinit var bottomNavigationHandler: BottomNavigationHandler
    lateinit var headerHandler: HeaderHandler

    lateinit var header:CustomHeader

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerHandler = (requireActivity() as MainActivity).getHeaderHandler()
        bottomNavigationHandler = (requireActivity() as MainActivity).getBottomNavigationHandler()
        header = (requireActivity() as MainActivity).header
    }

}