package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.Animal
import com.example.databinding.model.UserHandlerInterface
import com.example.databinding.model.UserModel

class MainActivity : AppCompatActivity(), UserHandlerInterface {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mUser = UserModel("Tuan", this)
        binding.user = mUser
        val mAnimal = Animal("Dog")
        binding.animal = mAnimal


    }

    override fun clickUser(v: View) {
        super.clickUser(v)
        Toast.makeText(this, "Basic", Toast.LENGTH_SHORT).show()
    }

    override fun clickWithNoParam() {
        super.clickWithNoParam()
        Toast.makeText(this, "No Param", Toast.LENGTH_SHORT).show()
    }

    override fun clickWithParam(s: String) {
        super.clickWithParam(s)
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }


}