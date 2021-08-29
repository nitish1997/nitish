package com.test.mvvmtest.repository
import com.test.mvvmtest.datamodel.Postresponse
import com.test.mvvmtest.ui.PostDataCallBack

interface PostRepository {
    fun selectPost(callBack: PostDataCallBack<Postresponse>)
    fun cancel()
}