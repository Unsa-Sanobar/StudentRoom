package com.example.studentroom.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentroom.R
import com.example.studentroom.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {
  
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_View)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        val floatingButton = view.findViewById(R.id.floatingActionbtn) as FloatingActionButton
        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"Successfully removed everything!",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_->
        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }


}