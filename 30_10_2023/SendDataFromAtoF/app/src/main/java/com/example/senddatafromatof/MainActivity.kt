package com.example.senddatafromatof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.senddatafromatof.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(binding.frameLayout.id,TestFragment()).commit()


        binding.btnSubmit.setOnClickListener{
            val fragment = TestFragment()
            val bundle = Bundle()
            bundle.putString("data", binding.emailInput.text.toString())
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(binding.frameLayout.id,fragment).commit()
        }
    }

    fun getEmail(): EditText{
        return binding.emailInput
    }
}