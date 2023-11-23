package com.example.demoviewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoviewpager2.model.Item

class HomePagerAdapter (val images: ArrayList<Item>) : RecyclerView.Adapter<HomePagerAdapter.HomePagerViewHolder>(){

    inner class  HomePagerViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img_res = v.findViewById<ImageView>(R.id.imgViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.img_item_home_fragment, parent, false)
        return HomePagerViewHolder(v)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: HomePagerViewHolder, position: Int) {
        val currImg = images[position]
        holder.img_res.setImageResource(currImg.image)
    }
}