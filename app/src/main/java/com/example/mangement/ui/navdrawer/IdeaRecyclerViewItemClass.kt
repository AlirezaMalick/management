package com.example.mangement.ui.navdrawer

import android.graphics.drawable.Drawable

class IdeaRecyclerViewItemClass(
    var item_image: Drawable?,
    var item_title: String,
    var optionType: OptionType
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

sealed class OptionType{

    object HOME : OptionType()
    object SETTINGS : OptionType()
    object CHAT : OptionType()
    object STATUS : OptionType()


}