package com.example.phonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.databinding.ActivityMainBinding
import com.example.phonebook.model.UserData
import com.example.phonebook.storage.MySharedPreferences
import com.example.phonebook.view.UserAdapter
import com.example.phonebook.view.UserListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), UserListAdapter.DeleteClickInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recv: RecyclerView
    private lateinit var userList: MutableList<UserData>
    private lateinit var userAdapter: UserAdapter
    private lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val creatBtn: FloatingActionButton = binding.addingBtn
        setContentView(binding.root)
        val mySharedPreferences = MySharedPreferences(this)


        recv = binding.mRecycler
        recv.setHasFixedSize(true)
        recv.layoutManager = LinearLayoutManager(this)

        // RecyclerView.adapter
        userList = mySharedPreferences.getListUser()
//        userAdapter = UserAdapter(this, userList)
//
//        recv.adapter = userAdapter

        // ListAdapter

        userListAdapter = UserListAdapter(this, userList,this)

        recv.adapter = userListAdapter
        userListAdapter.submitList(userList)

        creatBtn.setOnClickListener {
            addUser(mySharedPreferences)
        }


    }

    private fun addUser(mySharedPreferences: MySharedPreferences) {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNum = v.findViewById<EditText>(R.id.userNum)

        val addDiaLog = AlertDialog.Builder(this)
        addDiaLog.setView(v)

        addDiaLog.setPositiveButton("OK") { dialog, _ ->
            val name = userName.text.toString()
            val num = userNum.text.toString()
            userList.add(UserData(name, num))
            userListAdapter.submitList(userList)
            userListAdapter.notifyItemInserted(userList.size -1)
            recv.scrollToPosition(userList.size -1)

//            Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }

        addDiaLog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }

        addDiaLog.create()
        addDiaLog.show()

    }

    override fun onPause() {
        super.onPause()
        val mySharedPreferences = MySharedPreferences(this)
        mySharedPreferences.seListtUser(userList)

    }

    override fun onDelete(position: Int) {
          userList.removeAt(position)
          userListAdapter.submitList(userList)
    }


}