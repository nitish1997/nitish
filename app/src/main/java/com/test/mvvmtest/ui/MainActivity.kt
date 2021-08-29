package com.test.mvvmtest.ui
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mvvmtest.R
import com.test.mvvmtest.adapter.PostAdapter
import com.test.mvvmtest.database.entity.PostTable
import com.test.mvvmtest.datamodel.PostX
import com.test.mvvmtest.di.injection
class MainActivity : AppCompatActivity() {
    private lateinit var mainactivityViewModel: MainactivityViewModel
    private lateinit var postAdapter: PostAdapter
    private lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setUpUI()
    }
    override fun onResume() {
        super.onResume()
        if(isOnline(this))
        {
            mainactivityViewModel.loadstoredata()
        }
        else
        {
            mainactivityViewModel.allData.observe(this, Observer {
                var aryData = ArrayList<PostX>()
                for (i in 0 until it.size)
                {
                    aryData.add(PostX(it[i].event_date,it[i].event_name,it[i].id,it[i].likes,it[i].shares,it[i].thumbnail_image,it[i].views))
                }
                postAdapter.update(aryData)
            })
        }
    }
    fun setUpUI()
    {
        recyclerview =findViewById(R.id.recyclerview)
        postAdapter= PostAdapter(this,mainactivityViewModel.postDetails.value?: emptyList())
        recyclerview.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyclerview.adapter=postAdapter
    }
    fun setUpViewModel()
    {
        mainactivityViewModel = ViewModelProvider(this,injection.providePostViewModelFactory(this)).get(MainactivityViewModel::class.java)
        mainactivityViewModel.isViewLoading.observe(this, Observer {
            if(it)
            {

            }
            else
            {

            }
        })
        mainactivityViewModel.onMessageError.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            mainactivityViewModel.allData.observe(this, Observer {
                var aryData = ArrayList<PostX>()
                for (i in 0 until it.size)
                {

                    aryData.add(PostX(it[i].event_date,it[i].event_name,it[i].id,it[i].likes,it[i].shares,it[i].thumbnail_image,it[i].views))
                }
                postAdapter.update(aryData)
            })
        })
        mainactivityViewModel.postDetails.observe(this, Observer {
            postAdapter.update(it)
            for (i in 0 until it.size)
            {
                mainactivityViewModel.insertPost(PostTable(it[i].id,it[i].thumbnail_image,it[i].event_name,it[i].event_date.toString(),it[i].views,it[i].likes,it[i].shares))
            }
        })
    }
    @SuppressLint("MissingPermission")
    fun isOnline(context: Context): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}