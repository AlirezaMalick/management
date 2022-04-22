package com.example.mangement.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mangement.databinding.ActivityLoginBinding
import com.example.mangement.repo.ApiResponse
import com.example.mangement.ui.activity.forget.ForgetPasswordActivity
import com.example.mangement.ui.activity.signup.SignUpActivity
import com.example.mangement.ui.activity.dashboard.MainActivity
import com.example.mangement.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val viewModel by viewModels<LoginActivityViewModel>()
    lateinit var binding: ActivityLoginBinding

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        setupViews()

        setListeners()

        setObservers()

    }

    private fun setupViews() {
        mAuth = FirebaseAuth.getInstance();
        binding.layEmailAddress.setText("")
        binding.layPassword.setText("")

    }

    private fun setListeners() {
        binding.Signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setObservers() {
        viewModel.apply {
            loginModelResponce.observe(this@LoginActivity) {

                when (it) {
                    is ApiResponse.Success -> {
                        progressLiveData.postValue(false)
                        if (it.data?.getContentIfNotHandled() != null) {

                            val emails: String = it.data.peekContent().email?:""
                            val passwords: String = it.data.peekContent().password?:""

                            mAuth.signInWithEmailAndPassword(emails, passwords)
                                .addOnCompleteListener { task ->
                                    if (!task.isSuccessful) {

                                        showToast(this@LoginActivity, "Login not successfull")
                                    } else {
                                        binding.layEmailAddress.setText("")
                                        binding.layPassword.setText("")
//                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                        addDataToFirestore("ali","hhhh")
                                    }
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


    private fun addDataToFirestore(name: String, email: String) {
        val dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
        val userData:HashMap<String,String> = HashMap<String,String>()
        userData.put("UserName",name)
        userData.put("UserEmail",email)
        dataBase.collection("User")

//            .document("UserInfo")
            .add(userData)

            .addOnSuccessListener {
                Log.d("dataBaseFirestore", "addDataToFirestore: $it")
            }
            .addOnFailureListener {
                Log.d("dataBaseFirestore", "addDataToFirestore: ${it.message}")
            }


    }


}






