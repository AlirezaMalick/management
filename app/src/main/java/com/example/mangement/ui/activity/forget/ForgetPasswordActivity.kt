package com.example.mangement.ui.activity.forget

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mangement.databinding.ActivityForgetPasswordBinding
import com.example.mangement.repo.ApiResponse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgetPasswordActivity : AppCompatActivity() {
    val viewModel by viewModels<ForgetPasswordActivityViewModel>()
    lateinit var binding: ActivityForgetPasswordBinding

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
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

    }

    private fun setListeners() {

    }

    private fun setObservers() {
        viewModel.apply {
            registerModelResponse.observe(this@ForgetPasswordActivity) {

                when (it) {
                    is ApiResponse.Success -> {
                        progressLiveData.postValue(false)
                        if (it.data?.getContentIfNotHandled() != null) {

                            val emails: String = it.data.peekContent().email?:""


                            mAuth.sendPasswordResetEmail(emails)
                                .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this@ForgetPasswordActivity,
                                            "We have sent you instructions to reset your password!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this@ForgetPasswordActivity,
                                            "Failed to send reset email!",
                                            Toast.LENGTH_SHORT
                                        ).show()
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
}