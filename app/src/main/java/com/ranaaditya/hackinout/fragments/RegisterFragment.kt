package com.ranaaditya.hackinout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranaaditya.hackinout.R
import com.ranaaditya.hackinout.databinding.FragmentLoginBinding
import com.ranaaditya.hackinout.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        binding.registerbutton.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2()

            findNavController().navigate(action)

        }
    }
}