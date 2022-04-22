package com.example.mangement.ui.navdrawer.customView

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mangement.R
import com.example.mangement.utils.clickToActions
import com.example.mangement.utils.hide
import com.example.mangement.utils.inflates
import com.example.mangement.utils.show
import kotlinx.android.synthetic.main.custom_header.view.*

class CustomHeader @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout
    (context, attributeSet, defStyleRes) {


    var isMapView : Boolean =false
    var isMapViewSgd : Boolean =false

    var drawerOption: HeaderOptions = HeaderOptions.SHOW_DRAWER
        set(value) {
            field = value
            attachLeftIconClickListener()
        }

    private fun attachLeftIconClickListener() {
        leftDrawers.clickToActions {
            leftItemClickListener(drawerOption)
        }
    }

    var leftItemClickListener: (HeaderOptions) -> Unit = { _ -> }
    var searchItemClickListener: () -> Unit = {}
    var toggleItemClickListener: () -> Unit = {}
    var moreOptionsItemClickListener: () -> Unit = {}
    var searchInputBackItemClickListener: () -> Unit = {}
    init {
        inflates(R.layout.custom_header)



//        ic_toggle_map.clickToAction {
//            toggleItemClickListener()
//        }





    }

    private fun attachClickListeners() {
        attachLeftIconClickListener()
    }









    private fun showDrawerIcon() {
        leftDrawers.show()
    }

    private fun hideDrawerIcon(){
        leftDrawers.hide()
    }

    private fun showUserNameAndImage(){
        userNameAndRoleCl.show()
    }

    private fun hideUserNameAndImage(){
        userNameAndRoleCl.hide()
    }





    fun toggleOptions(option: HeaderOptions) {
        when (option) {
            HeaderOptions.SHOW_DRAWER -> showDrawerIcon()
            HeaderOptions.HIDE_DRAWER -> hideDrawerIcon()


            HeaderOptions.SHOW_USER_NAME -> showUserNameAndImage()
            HeaderOptions.HIDE_USER_NAME -> hideUserNameAndImage()

        }
        attachClickListeners()
    }





}