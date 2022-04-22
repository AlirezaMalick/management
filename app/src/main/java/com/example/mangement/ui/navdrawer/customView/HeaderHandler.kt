package com.example.mangement.ui.navdrawer.customView

interface HeaderHandler {

    fun setOptions(vararg headerOptions: HeaderOptions) {

    }


    fun setMapIcon(vararg showMapIcon: ShowMapIcon)
    {

    }

    fun hideMapIcon(vararg hideMapIcon: HideMapIcon)
    {

    }

    fun visibility(headerVisibility: HeaderVisibility) {

    }
}

enum class HeaderVisibility {
    SHOW,
    HIDE,
    INVISIBLE
}

enum class HeaderOptions {
    SHOW_SEARCH_ICON,
    HIDE_SEARCH_ICON,
    SHOW_MAP_ICON,
    HIDE_MAP_ICON,
    SHOW_DRAWER,
    HIDE_DRAWER,
    SHOW_USER_NAME,
    HIDE_USER_NAME,
    SHOW_SEARCH_VIEW,
    HIDE_SEARCH_VIEW;
}


enum class ShowMapIcon
{
    SHOW_MAP_ICON
}

enum class HideMapIcon
{
    HIDE_MAP_ICON
}