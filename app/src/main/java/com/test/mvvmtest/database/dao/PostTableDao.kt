package com.test.mvvmtest.database.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.mvvmtest.database.entity.PostTable
import com.test.mvvmtest.datamodel.PostX
import com.test.mvvmtest.datamodel.Postresponse

@Dao
interface PostTableDao {
    @Query("Select * from PostTable")
    fun getPost():LiveData<List<PostX>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun SavePost(postt: PostTable)
}