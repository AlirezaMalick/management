package com.example.mangement.ui.fargment.chat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mangement.databinding.FragmentChatBinding
import com.example.mangement.utils.Constant
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment : Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null

    lateinit var binding: FragmentChatBinding
    val viewModel by viewModels<ChatFragmentViewModel>()


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance() =
            ChatFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        getUser()
    }


    private fun setUpViews(){



        binding.fab.setOnClickListener {

        }
    }


    private fun getUser(){
        val database:FirebaseFirestore = FirebaseFirestore.getInstance()
        database.collection(Constant.USER)
            .get()
            .addOnCompleteListener { task->

                val userID:String


            }
    }




}