package com.example.animalapp.ui.auth_user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentSignupBinding
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    private val viewModel: SignupViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignupBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        checkEnableSubmitBtn()
        observeModel()

    }

    private fun checkEnableSubmitBtn() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEnableRequire()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        binding.editMailInput.addTextChangedListener(textWatcher)
        binding.editPw.addTextChangedListener(textWatcher)
        binding.editNameInput.addTextChangedListener(textWatcher)
        binding.cbSignup.setOnCheckedChangeListener { _, _ -> checkEnableRequire() }
    }

    private fun checkEnableRequire() {
        val email = binding.editMailInput.text.toString().trim()
        val pwd = binding.editPw.text.toString().trim()
        val name = binding.editNameInput.text.toString().trim()
        val isChecked = binding.cbSignup.isChecked
        if (name.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && isChecked) {
            binding.btnSignupSubmit.isEnabled = true
            binding.btnSignupSubmit.setBackgroundColor(resources.getColor(R.color.enable_btn))
            binding.btnSignupSubmit.setOnClickListener {
                viewModel.registerUser(name, email, pwd)
            }
        } else {
            binding.btnSignupSubmit.isEnabled = false
            binding.btnSignupSubmit.setBackgroundColor(resources.getColor(R.color.disable_btn))
            binding.btnSignupSubmit.setTextColor(resources.getColor(R.color.white))
        }
    }
    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), it.data?.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }


}