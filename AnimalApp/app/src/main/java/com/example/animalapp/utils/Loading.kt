package com.example.animalapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.BlendModeColorFilter
import android.graphics.BlendMode
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Build
import android.view.Window
import com.example.animalapp.R
import androidx.core.content.ContextCompat
import com.example.animalapp.databinding.CusLoadingViewBinding

class Loading(context: Context, themeResId: Int) : Dialog(context, themeResId) {
    private lateinit var binding: CusLoadingViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CusLoadingViewBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.cus_loading_view)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            binding.progressBar.indeterminateDrawable.colorFilter = BlendModeColorFilter(
                ContextCompat.getColor(context, R.color.white),
                BlendMode.SRC_ATOP
            )
        else binding.progressBar.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(
                context,
                android.R.color.white
            ), PorterDuff.Mode.SRC_ATOP
        )
    }
}