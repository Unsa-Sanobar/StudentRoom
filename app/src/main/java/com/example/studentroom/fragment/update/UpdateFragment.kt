package com.example.studentroom.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentroom.R
import com.example.studentroom.model.User
import com.example.studentroom.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateFirstName: EditText
    private lateinit var updateLastName: EditText
    private lateinit var updateFatherName: EditText
    private lateinit var updateSemester : EditText
    private lateinit var UpdateDegree: EditText
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        updateFirstName = view.findViewById(R.id.updatefirstName)
        updateLastName = view.findViewById(R.id.updatelastName)
        updateFatherName = view.findViewById(R.id.updatefatherName)
        updateSemester = view.findViewById(R.id.updatesemester)
        UpdateDegree = view.findViewById(R.id.updatedegree)
        val updateBtn = view.findViewById<Button>(R.id.updatebtn)

        updateFirstName.setText(args.currentUser.firstName)
        updateLastName.setText(args.currentUser.lastName)
        updateFatherName.setText(args.currentUser.fatherName)
        updateSemester.setText(args.currentUser.semester.toString())
        UpdateDegree.setText(args.currentUser.degreeName)

        updateBtn.setOnClickListener {
            //call this method when you click on update
            updateItem()
        }
        setHasOptionsMenu(true)



        return view
    }
    private fun updateItem(){
        val firstName = updateFirstName.text.toString()
        val lastName = updateLastName.text.toString()
        val fatherName = updateFatherName.text.toString()
        val semester = Integer.parseInt(updateSemester.text.toString())
        val degree= UpdateDegree.text.toString()
        if (inputCheck(firstName,lastName,fatherName,updateSemester.text,degree)){

            val updatedUser = User(args.currentUser.id,firstName,lastName,fatherName,semester,degree)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated Successfully!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, fatherName: String, semester: Editable, degree: String): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(fatherName) || semester.isEmpty() || TextUtils.isEmpty(degree))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            deleteUaser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUaser() {

            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_,_->
                mUserViewModel.deleteUser(args.currentUser)
                Toast.makeText(requireContext(),"Successfully removed! ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("No"){_,_->
            }
            builder.setTitle("Delete ${args.currentUser.firstName}?")
            builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
            builder.create().show()

    }
}