package com.example.studentroom.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentroom.R
import com.example.studentroom.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder> (){
    private var userList = emptyList<User>()

    class MyViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text=currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.lastname_txt).text=currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.fathername_txt).text=currentItem.fatherName
        holder.itemView.findViewById<TextView>(R.id.semestertxt).text=currentItem.semester.toString()
        holder.itemView.findViewById<TextView>(R.id.Depttxt).text=currentItem.degreeName

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            //actionListFragment might give you error but you only need to rebuild the project
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }
    fun setData(user : List<User>){
        this.userList= user
        notifyDataSetChanged()
    }

}