package com.test.mvvmtest.model
import com.test.mvvmtest.database.PostTableRepository
import com.test.mvvmtest.datamodel.Postresponse
import com.test.mvvmtest.repository.PostRepository
import com.test.mvvmtest.retro.ApiClient
import com.test.mvvmtest.ui.PostDataCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class PostCallFromServer :PostRepository {
    private var call: Call<Postresponse>?=null
    override fun selectPost(
        callback: PostDataCallBack<Postresponse>
    ) {
        call= ApiClient.build()?.getPostData()
        call!!.enqueue(object : Callback<Postresponse> {
            override fun onFailure(call: Call<Postresponse>, t: Throwable) {
                callback.OnError("Data Not Found")
            }
            override fun onResponse(
                call: Call<Postresponse>,
                response: Response<Postresponse>
            ) {
                response.body()?.let {
                    if (it.posts.size>0)
                    {
                        callback.OnSuccess(it)
                    }
                    else{
                        callback.OnError("Didn't place any order")
                    }
                }
            }
        })
    }

    override fun cancel() {

    }
}