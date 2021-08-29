package com.test.mvvmtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.mvvmtest.R
import com.test.mvvmtest.datamodel.PostX
import kotlinx.android.synthetic.main.timiline_item.view.*
class PostAdapter (var context: Context,var postData:List<PostX>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            fun bind(postList: PostX)
            {
                itemView.title.text=postList.event_name
                itemView.viewh.text="Views "+postList.views
                itemView.likecount.text="Like "+postList.likes
                itemView.sharecount.text="Share "+postList.shares
                if (postList.thumbnail_image !== null) {
                    Glide.with(itemView.context)
                        .load(postList.thumbnail_image)
                        .into(itemView.image)
                } else {
                    itemView.image.setImageResource(R.drawable.ic_launcher_background)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view= LayoutInflater.from(context).inflate(R.layout.timiline_item,parent,false)
            return ViewHolder(view)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(postData[position])
        }
        override fun getItemCount(): Int {
            return postData.size
        }
    fun update(postData:List<PostX>)
    {
        this.postData=postData
        notifyDataSetChanged()
    }
}