package com.target.app.estidama.customviews.navdrawer

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mangement.R
import com.example.mangement.ui.navdrawer.customView.navdrawer.DrawerFragmentViewModel
import com.example.mangement.databinding.FragmentDrawerBinding


class DrawerFragment : Fragment(), DrawerAdapter.OnDrawerItemClickListener {
    lateinit var binding: FragmentDrawerBinding
    val viewModel by viewModels<DrawerFragmentViewModel>()
    private lateinit var adapter: DrawerAdapter

    private var drawerListener: FragmentDrawerListener? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            drawerListener = context as FragmentDrawerListener
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrawerBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()

        adapter = DrawerAdapter(requireContext(), getDrawerItems(), this)
        binding.rvDrawerItems.adapter = adapter



        fillUserInfo()
    }

    private fun fillUserInfo() {

    }

    private fun setObservers() {
        viewModel.onLogoutLiveData.observe(viewLifecycleOwner){
            if(it){
                logout()
                viewModel.onLogoutLiveData.postValue(false)
            }
        }

        viewModel.onCloseDrawerLiveData.observe(viewLifecycleOwner){
            mDrawerLayout?.closeDrawer(containerView!!)
        }


    }


    fun logout()
    {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setMessage(getString(R.string.logout))
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener { dialog, id ->
                drawerListener?.onSignOut()
                dialog.cancel() })

        builder1.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    fun init(fragmentId: Int, drawerLayout: DrawerLayout) {
        containerView = activity?.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerLayout!!.addDrawerListener(object : DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })

    }

    private fun getDrawerItems(): ArrayList<DrawerItem> {
        val statusFilters = arrayListOf<DrawerItem>()
        statusFilters.add(DrawerItem("0", getString(R.string.view_profile), R.drawable.ic_placeholder, DrawerItemType.ViewProfile))
       // statusFilters.add(DrawerItem("1", getString(R.string.change_org), R.drawable.ic_change_organization, DrawerItemType.ChangeOrganisation))
//        statusFilters.add(DrawerItem("2", getString(R.string.saved_jobs), R.drawable.ic_saved_job, DrawerItemType.SavedJobs))
//        statusFilters.add(DrawerItem("3", getString(R.string.saved_searches), R.drawable.ic_saveds_searches, DrawerItemType.SavedSearches))
//        statusFilters.add(DrawerItem("4", getString(R.string.contact_support), R.drawable.ic_contact_support, DrawerItemType.ContactSupport))
//        statusFilters.add(DrawerItem("5", getString(R.string.online_chat), R.drawable.ic_online_chat, DrawerItemType.OnlineChat))
//        statusFilters.add(DrawerItem("6", getString(R.string.documentation), R.drawable.ic_documentation, DrawerItemType.Documentation))
       // statusFilters.add(DrawerItem("7", getString(R.string.connect_to_web), R.mipmap.ic_launcher, DrawerItemType.ConnectToWeb))

        return statusFilters
    }

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(drawerItem: DrawerItem)
        fun onSignOut()
    }

    override fun onDrawerItemClick(drawerItem: DrawerItem) {
        mDrawerLayout?.closeDrawer(containerView!!)
        drawerListener?.onDrawerItemSelected(drawerItem)
    }
}