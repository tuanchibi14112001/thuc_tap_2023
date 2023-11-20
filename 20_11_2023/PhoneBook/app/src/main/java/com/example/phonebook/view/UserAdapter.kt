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
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.R
import com.example.phonebook.model.UserData


class UserAdapter(val context: Context, val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var name: TextView
        var phone: TextView
        var mMenu: ImageView

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            phone = v.findViewById<TextView>(R.id.mSubTitle)
            mMenu = v.findViewById<ImageView>(R.id.mMenus)
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
                        var userName = v.findViewById<EditText>(R.id.userName)
                        var userNum = v.findViewById<EditText>(R.id.userNum)

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
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.name.text = currentItem.userName
        holder.phone.text = currentItem.userPhoneNum
    }
}