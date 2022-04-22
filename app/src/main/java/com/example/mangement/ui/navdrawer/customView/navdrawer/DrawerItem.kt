package com.target.app.estidama.customviews.navdrawer

data class DrawerItem(var id: String = "", var title: String = "", var img: Int = -1, val drawerItemType: DrawerItemType)

sealed class DrawerItemType{
    object ViewProfile: DrawerItemType()
    object ChangeOrganisation: DrawerItemType()
    object SavedJobs: DrawerItemType()
    object SavedSearches: DrawerItemType()
    object ContactSupport: DrawerItemType()
    object OnlineChat: DrawerItemType()
    object Documentation: DrawerItemType()
    object ConnectToWeb: DrawerItemType()
}
