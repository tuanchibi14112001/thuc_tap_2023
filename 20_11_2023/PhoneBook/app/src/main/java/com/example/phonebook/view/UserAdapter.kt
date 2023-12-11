package com.example.phonebook.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.R
import com.example.phonebook.databinding.ListItemBinding
import com.example.phonebook.model.UserData


class UserAdapter(val context: Context, val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    inner class UserViewHolder(listItemBinding: ListItemBinding) : RecyclerView.ViewHolder(
        listItemBinding.root
    ) {

        var name: TextView
        var phone: TextView
        var mMenu: ImageView
        var mlistItemBinding: ListItemBinding

        init {
            mlistItemBinding = listItemBinding
            name = listItemBinding.mTitle
            phone = listItemBinding.mSubTitle
            mMenu = listItemBinding.mMenus
            mMenu.setOnClickListener {
                popupMenu(it)
            }
        }

        @SuppressLint("DiscouragedPrivateApi")
        private fun popupMenu(view: View) {

            val position = userList[absoluteAdapterPosition]

            val popupMenu = PopupMenu(context, view)
            popupMenu.inflate(R.menu.show_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editTxt -> {
                        val v = LayoutInflater.from(context).inflate(R.layout.add_item, null)
                        val userName = v.findViewById<EditText>(R.id.userName)
                        val userNum = v.findViewById<EditText>(R.id.userNum)

                        userName.setText(position.userName)
                        userNum.setText(position.userPhoneNum)
                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("OK") { dialog, _ ->
                                run {
                                    position.userName = userName.text.toString()
                                    position.userPhoneNum = userNum.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(
                                        context,
                                        "User Information is Edited",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    dialog.dismiss()
                                }
                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                run {
                                    dialog.dismiss()
                                    Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .create()
                            .show()

                        true
                    }

                    R.id.delete -> {
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure?")
                            .setPositiveButton("YES") { dialog, _ ->
                                run {
                                    userList.removeAt(bindingAdapterPosition)
                                    notifyDataSetChanged()
                                    Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()

                                }
                            }
                            .setNegativeButton("NO") { dialog, _ ->
                                run {
                                    dialog.dismiss()

                                }
                            }
                            .create()
                            .show()

                        true
                    }

                    else -> true
                }
            }
            popupMenu.show()

            // Them Icon vao popup menu
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = ListItemBinding.inflate(inflater, parent,false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.mlistItemBinding.user = currentItem

    }
}