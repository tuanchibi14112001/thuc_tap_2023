package com.example.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CalcViewModel.CalcViewModelFactory(application)
        )[CalcViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            btnCancel.setOnClickListener {
                viewModel.cancel()
            }

            btnHandling.setOnClickListener {
                val numA = binding.edtNumA.text.toString().toInt()
                val numB = binding.edtNumB.text.toString().toInt()

                viewModel.add(numA, numB)

            }

            btnSeeResult.setOnClickListener {
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(RESULT, viewModel.result)
                startActivity(intent)
            }
        }

        viewModel.outputWorkInfos.observe(this) { listWorkInfo ->
            if (listWorkInfo.isNullOrEmpty()) {
                return@observe
            }
            val workInfo = listWorkInfo[0]
            if(workInfo.state.isFinished){
                showWorkFinished()
                val result = workInfo.outputData.getInt(SUMMATION, -1)
                if(result != -1){
                    binding.btnSeeResult.visibility = View.VISIBLE
                    viewModel.result = result
                }
            } else{
                showWorkInProgress()
            }


        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            btnHandling.visibility = View.GONE
            btnCancel.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            btnSeeResult.visibility = View.GONE
        }
    }

    private fun showWorkFinished() {
        with(binding) {
            btnHandling.visibility = View.VISIBLE
            btnCancel.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }
}