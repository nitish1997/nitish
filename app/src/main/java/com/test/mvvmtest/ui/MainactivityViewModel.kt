package com.test.mvvmtest.ui
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvmtest.database.MainDatabase
import com.test.mvvmtest.database.PostTableRepository
import com.test.mvvmtest.database.entity.PostTable
import com.test.mvvmtest.datamodel.PostX
import com.test.mvvmtest.datamodel.Postresponse
import com.test.mvvmtest.repository.PostRepository
import kotlinx.coroutines.launch

class MainactivityViewModel (var context : Context,var repository:PostRepository):ViewModel(){
    val postTableDao = MainDatabase.getDatabase(context).postDao()
    private val _postDetail = MutableLiveData<List<PostX>>()
    val postDetails: LiveData<List<PostX>> = _postDetail
    val postTableRepository : PostTableRepository = PostTableRepository(postTableDao)

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<String>()
    val onMessageError:LiveData<String> = _onMessageError

    val allData : LiveData<List<PostX>> = postTableRepository!!.postSelect
    fun insertPost(postt : PostTable) = viewModelScope.launch {
        postTableRepository.insertPost(postt)
    }
    fun loadstoredata(){
        _isViewLoading.postValue(true)
        repository.selectPost(object :PostDataCallBack<Postresponse>{
          override fun OnSuccess(data: Postresponse?) {
              _isViewLoading.postValue(false)
                if(data!=null){
                    _postDetail.value= data.posts
                }
                else{
                    _onMessageError.postValue("Product Not Found")
                }
            }

            override fun OnError(Error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( Error)
            }

        })
    }
}