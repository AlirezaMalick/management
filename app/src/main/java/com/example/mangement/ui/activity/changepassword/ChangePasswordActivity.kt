package com.example.mangement.ui.activity.changepassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mangement.databinding.ActivityChangePasswordBinding
import com.example.mangement.repo.ApiResponse
import com.example.mangement.ui.activity.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {
    val viewModel by viewModels<ChangePasswordActivityViewModel>()
    lateinit var binding: ActivityChangePasswordBinding

    lateinit var mAuth: FirebaseAuth

    var user:FirebaseUser?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        setupViews()

        setListeners()

        setObservers()
    }

    private fun setupViews() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            // User is logged in
        }
         user = FirebaseAuth.getInstance().currentUser
        binding.layPassword.setText("")

    }

    private fun setListeners() {

    }

    private fun setObservers() {
        viewModel.apply {
            registerModelResponse.observe(this@ChangePasswordActivity) {

                when (it) {
                    is ApiResponse.Success -> {
                        progressLiveData.postValue(false)
                        if (it.data?.getContentIfNotHandled() != null) {

                            val passwords: String = it.data.peekContent().password?:""

                            if (user != null && !passwords.trim().equals("")) {
                                    user?.updatePassword(passwords.trim())?.addOnCompleteListener(OnCompleteListener<Void?> { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(
                                                    this@ChangePasswordActivity,
                                                    "Password is updated, sign in with new password!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                signOut()
                                            } else {
                                                Toast.makeText(
                                                    this@ChangePasswordActivity,
                                                    "Failed to update password!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        })

                            } else if (passwords.trim().equals("")) {
                                binding.layPassword.setError("Enter password")

                            }


                        }

                    }

                    is ApiResponse.Error -> {
                        progressLiveData.postValue(false)

                    }

                    is ApiResponse.Loading -> {
                        progressLiveData.postValue(true)
                    }

                }
            }

        }
    }

    //sign out method
    fun signOut() {
//        mAuth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}