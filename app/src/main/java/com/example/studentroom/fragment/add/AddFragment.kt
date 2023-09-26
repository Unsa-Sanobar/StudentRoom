package com.example.studentroom.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.studentroom.R
import com.example.studentroom.model.User
import com.example.studentroom.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val addbtn = view.findViewById<Button>(R.id.addbtn)
        addbtn.setOnClickListener {
            insertDataToDatabase()

        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstNameEditText = view?.findViewById<EditText>(R.id.firstName)
        val lastNameEditText = view?.findViewById<EditText>(R.id.lastName)
        val fatherNameEditText = view?.findViewById<EditText>(R.id.fatherName)
        val semesterName = view?.findViewById<EditText>(R.id.semester)
        val degreeName = view?.findViewById<EditText>(R.id.degree)
        val firstName = firstNameEditText?.text.toString()
        val lastName = lastNameEditText?.text.toString()
        val fatherName = fatherNameEditText?.text.toString()
        val semester = semesterName?.text.toString()
        val degree = degreeName?.text.toString()
        if(inputCheck(firstName,lastName,fatherName,semester,degree)){
            val user = User(0,firstName,lastName,fatherName,Integer.parseInt(semester.toString()),degree)

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out more feilds.", Toast.LENGTH_SHORT).show()
        }

    }
    private fun inputCheck(firstName: String, lastName: String, fatherName: String, semester: String, degree: String): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(fatherName) || semester.isEmpty() || TextUtils.isEmpty(degree))
    }
}

