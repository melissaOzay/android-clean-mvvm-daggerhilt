package com.example.mvvmapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmapp.R
import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.view_model.SaveVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private val saveViewModel by lazy {
        ViewModelProvider(this)[SaveVM::class.java]
    }

    //    private val Savebutton by lazy {
//       view?.findViewById<Button>(R.id.button)
//    }
    private lateinit var savebutton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save, container, false)
        savebutton = view.findViewById(R.id.button)
        saveButton()
        return view
    }

    private fun saveButton() {

        savebutton.setOnClickListener {
            createUser()
            Navigation.findNavController(it).navigate(R.id.userEkranınaGecis)
        }
    }

    private fun createUser() {
        val name: TextView = requireView().findViewById(R.id.nameText)
        val email: TextView = requireView().findViewById(R.id.emailText)
        val password: TextView = requireView().findViewById(R.id.passText)
        val surname: TextView = requireView().findViewById(R.id.surnameText)

        val nameText = name.text.toString()
        val emailText = email.text.toString()
        val passwordText = password.text.toString()
        val surnameText = surname.text.toString()

        val user = UserInfo(id = null, nameText, surnameText, emailText, passwordText)
        saveViewModel.createNewUser(user)
        saveViewModel.userListInfo.observe(requireActivity()) {
            Toast.makeText(requireActivity(), "Successfully created User", Toast.LENGTH_LONG).show()
        }


        saveViewModel.failure.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

    }


}