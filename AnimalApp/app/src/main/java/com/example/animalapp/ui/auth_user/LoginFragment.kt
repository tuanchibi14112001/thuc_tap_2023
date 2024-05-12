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
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentLoginBinding
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
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
    }

    private fun checkEnableRequire() {
        val email = binding.editMailInput.text.toString().trim()
        val pwd = binding.editPw.text.toString().trim()
        if (email.isNotEmpty() && pwd.isNotEmpty()) {
            binding.btnLoginSubmit.isEnabled = true
            binding.btnLoginSubmit.setBackgroundColor(resources.getColor(R.color.enable_btn))
            binding.btnLoginSubmit.setOnClickListener {
                viewModel.loginUser(email, pwd)
            }
        }
        else{
            binding.btnLoginSubmit.isEnabled = false
            binding.btnLoginSubmit.setBackgroundColor(resources.getColor(R.color.disable_btn))
            binding.btnLoginSubmit.setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), it.data?.token, Toast.LENGTH_SHORT).show()
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