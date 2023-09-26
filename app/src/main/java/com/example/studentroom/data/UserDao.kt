package com.example.studentroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studentroom.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addUser(user: User)

   @Update
   suspend fun updateUser(user: User)
   //delete single user
   @Delete
   suspend fun deleteUser(user: User)
   //delete all record through custom query
   @Query("DELETE FROM student_table")
   suspend fun deleteAllUsers()


   @Query("SELECT * FROM student_table ORDER BY id ASC")
   fun readAllData() : LiveData<List<User>>


}