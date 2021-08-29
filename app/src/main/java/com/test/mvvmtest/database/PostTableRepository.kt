package com.test.mvvmtest.database

import androidx.lifecycle.LiveData
import com.test.mvvmtest.database.dao.PostTableDao
import com.test.mvvmtest.database.entity.PostTable
import com.test.mvvmtest.datamodel.PostX
import com.test.mvvmtest.datamodel.Postresponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostTableRepository(private var postTableDao: PostTableDao) {
    val postSelect: LiveData<List<PostX>> = postTableDao.getPost()
    fun insertPost(post: PostTable)
    {
        GlobalScope.launch {
            postTableDao.SavePost(post)
        }
    }

}