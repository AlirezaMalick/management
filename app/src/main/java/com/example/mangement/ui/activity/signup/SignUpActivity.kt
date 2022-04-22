package com.example.mangement.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mangement.databinding.ActivitySignUpBinding
import com.example.mangement.repo.ApiResponse
import com.example.mangement.ui.activity.forget.ForgetPasswordActivity
import com.example.mangement.ui.activity.login.LoginActivity
import com.example.mangement.utils.PreferenceClass
import com.example.mangement.utils.showToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    val viewModel by viewModels<SignUpViewModel>()
    lateinit var binding: ActivitySignUpBinding
    lateinit var mAuth: FirebaseAuth

    val preferenceClass:PreferenceClass=PreferenceClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setupViews()

        setListeners()

        setObservers()
    }


    private fun setupViews() {
        mAuth = FirebaseAuth.getInstance();


    }

    private fun setListeners() {
        binding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setObservers() {
        viewModel.apply {
            registerModelResponse.observe(this@SignUpActivity) {

                when (it) {
                    is ApiResponse.Success -> {
                        progressLiveData.postValue(false)
                        if (it.data?.getContentIfNotHandled() != null) {

                            val name: String = it.data.peekContent().name?:""
                            val emails: String = it.data.peekContent().email?:""
                            val passwords: String = it.data.peekContent().password?:""

                            mAuth.createUserWithEmailAndPassword(emails, passwords)
                                .addOnCompleteListener(this@SignUpActivity, OnCompleteListener<AuthResult?> { task ->
                                        showToast(this@SignUpActivity, "createUserWithEmail:onComplete:" + task.isSuccessful)
                                        if (!task.isSuccessful) {
                                            showToast(this@SignUpActivity, "Authentication failed.")
                                        } else {
                                            showToast(this@SignUpActivity, "Sign Up Successful")
                                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                            addDataToFirestore(name,emails)
                                        }
                                    })

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