package com.example.mangement.ui.navdrawer

import android.graphics.drawable.Drawable

class RecyclerViewItemClass(
    var item_image: Drawable?,
    var item_title: String,
    var optionType: OptionTypoe
) {
    init {
        this.item_image=item_image
        this.item_title=item_title
    }

    fun itemimage(): Drawable?
    {
        return item_image
    }
    fun itemtitle():String
    {
        return item_title
    }



}

sealed class OptionTypoe{

    object LIKES : OptionTypoe()
    object SETTINGS : OptionTypoe()
    object ABOUT_US : OptionTypoe()
    object PRIVACY_POLICY : OptionTypoe()
    object TERMS_OF_USE : OptionTypoe()
    object FAQS : OptionTypoe()
}