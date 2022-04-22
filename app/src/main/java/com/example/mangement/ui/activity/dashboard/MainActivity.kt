package com.example.mangement.ui.activity.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mangement.R
import com.example.mangement.ui.navdrawer.customView.*
import com.example.mangement.databinding.ActivityTestMainBinding
import com.example.mangement.model.SelectedBottomNavigation
import com.example.mangement.ui.navdrawer.IdeaRecyclerViewAdapter
import com.example.mangement.ui.navdrawer.IdeaRecyclerViewItemClass
import com.example.mangement.ui.navdrawer.OptionType
import com.example.mangement.ui.activity.login.LoginActivity
import com.example.mangement.ui.fargment.chat.ChatFragment
import com.example.mangement.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.target.app.estidama.customviews.navdrawer.DrawerFragment
import com.target.app.estidama.customviews.navdrawer.DrawerItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_test_main.*
import kotlinx.android.synthetic.main.custom_header.view.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HeaderHandler, BottomNavigationHandler,
    DrawerFragment.FragmentDrawerListener, SearchView.OnQueryTextListener,
    IdeaRecyclerViewAdapter.InterfaceMyIdea {

    val viewModel by viewModels<MainActivityViewModel>()
    lateinit var binding: ActivityTestMainBinding

    private var selectedBottomNavigation: SelectedBottomNavigation = SelectedBottomNavigation.Home
    private lateinit var list: ArrayList<IdeaRecyclerViewItemClass>
    private lateinit var recyclerViewAdapter: IdeaRecyclerViewAdapter

    lateinit var mAuth: FirebaseAuth
    private var authListener: AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        FirebaseAuth()



        setUpViews()

        setupListeners()

//        navigate(GalleryFragment.newInstance())
    }

    private fun FirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();


        //get current user
        val user = FirebaseAuth.getInstance().currentUser

        authListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setUpViews() {


        binding.includedlayout.tvUserName.text = "Waleed"
//        binding.includedlayout.tvEmail.text = EstidamaController.INSTANCE?.userEmail
//        binding.includedlayout.tvAddress.text = EstidamaController.INSTANCE?.userAddress

        val url =
            "https://thumbs.dreamstime.com/b/businessman-icon-vector-male-avatar-profile-image-profile-businessman-icon-vector-male-avatar-profile-image-182095609.jpg"
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.avatar)
            .placeholder(R.drawable.avatar)
            .into(binding.includedlayout.userImage)


        list = arrayListOf()
        list.add(
            IdeaRecyclerViewItemClass(
                getDrawable(R.drawable.ic_home), getString(
                    R.string.menu_home
                ),
                OptionType.HOME
            )
        )
        list.add(
            IdeaRecyclerViewItemClass(
                getDrawable(R.drawable.ic_settings),
                getString(R.string.action_settings),
                OptionType.SETTINGS
            )
        )
        list.add(
            IdeaRecyclerViewItemClass(
                getDrawable(R.drawable.ic_chat),
                getString(R.string.chat),
                OptionType.CHAT
            )
        )
        list.add(
            IdeaRecyclerViewItemClass(
                getDrawable(R.drawable.ic_status),
                getString(R.string.status),
                OptionType.STATUS
            )
        )


        recyclerViewAdapter = IdeaRecyclerViewAdapter(list, this)
        binding.includedlayout.recyclerView.adapter = recyclerViewAdapter
        //    val user1 = UserInfo(com.target.estidama.R.drawable.ic_launcher_background, "", "", "")
        //binding.includedlayout.user = user1

        binding.includedlayout.logout.clickToAction {
            mAuth.signOut()



        }
    }

    fun getHeaderHandler(): HeaderHandler = this
    fun getBottomNavigationHandler(): BottomNavigationHandler = this
    override fun updateMenu(menuRes: Int) {
        TODO("Not yet implemented")
    }


    override fun visibility(headerVisibility: HeaderVisibility) {
        binding.header.visibility(HeaderVisibility.SHOW == headerVisibility)
    }

    override fun visibility(bottomNavigationVisibility: BottomNavigationVisibility) {
        when (bottomNavigationVisibility) {
            BottomNavigationVisibility.SHOW -> {
                bottomNavigationView.show()
            }
            BottomNavigationVisibility.HIDE -> {
                hideBottomNavigations()
            }
        }


    }

    override fun hideBottomNavigations() {
        bottomNavigationView.hide()
    }

    override fun onDrawerItemSelected(drawerItem: DrawerItem) {

    }

    override fun onSignOut() {

        mAuth.signOut()
        // this listener will be called when there is change in firebase user session
        // this listener will be called when there is change in firebase user session
        val authListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(
        fragment: BaseFragment,
        shouldRegisterToBackStack: Boolean
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment, fragment::class.java.simpleName)
        if (shouldRegisterToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }
        transaction.commit()
    }

    fun openDrawerMenu() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }


    private fun setupListeners() {
        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_status -> {
                    selectedBottomNavigation = SelectedBottomNavigation.Status


//                    navigate(GlobalIdeasFragment.newInstance())

                }
                R.id.nav_home -> {
                    selectedBottomNavigation = SelectedBottomNavigation.Home

//                    navigate(GlobalContributionFragment.newInstance())


                }
                R.id.nav_chat -> {
                    selectedBottomNavigation = SelectedBottomNavigation.Chat

//                    navigate(GlobalCategoryFragment.newInstance())
                    navigate(ChatFragment.newInstance())


                }


            }
            true
        }

        binding.header.leftDrawers.clickToActions {
            openDrawerMenu()
        }
    }

    override fun onListClicked(content: IdeaRecyclerViewItemClass) {
        when (content.optionType) {
            OptionType.HOME -> {
//                val intent = Intent(this, IdeaLikesActivity::class.java)
//                startActivity(intent)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            OptionType.SETTINGS -> {
//                val intent = Intent(this, IdeaAccountSettingsActivity::class.java)
//                startActivity(intent)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            OptionType.CHAT -> {
//                val intent = Intent(this, IdeaPrivacyPolicyActivity::class.java)
//                startActivity(intent)
                navigate(ChatFragment.newInstance())
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            OptionType.STATUS -> {
//                val intent = Intent(this, ActivityIdeaTermsOfUse::class.java)
//                startActivity(intent)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }




        }
    }




    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(authListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            mAuth.removeAuthStateListener(authListener!!)
        }
    }
}