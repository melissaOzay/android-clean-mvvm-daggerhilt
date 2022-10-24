package com.example.mvvmapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.ui.UserFragment
import java.util.*
import kotlin.collections.ArrayList

class AppAdapter :
    RecyclerView.Adapter<AppAdapter.CompanyViewHolder>() {
    private var userList = arrayListOf<UserInfo>()

    fun setData(userList: ArrayList<UserInfo>) {
        this.userList = userList
        userList.reverse()
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.name)
        val surnameText = view.findViewById<TextView>(R.id.surname)
        val emailText = view.findViewById<TextView>(R.id.email)
        val passwordText = view.findViewById<TextView>(R.id.password)

        fun bindItems(item: UserInfo) {

            nameText.text = item.name
            surnameText.text = item.surname
            emailText.text = item.email
            passwordText.text = item.password

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(userList.get(position))

    }

    override fun getItemCount(): Int {
        return userList.count()
    }

}