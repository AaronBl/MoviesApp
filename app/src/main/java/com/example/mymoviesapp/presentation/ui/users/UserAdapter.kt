package com.example.mymoviesapp.presentation.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.R
import com.example.mymoviesapp.data.room.UserEntity
import com.example.mymoviesapp.presentation.BaseViewHolder
import com.example.mymoviesapp.presentation.ui.movies.MoviesAdapter

class UserAdapter(
    private val context: Context,
    private val list: List<UserEntity>
) : RecyclerView.Adapter<BaseViewHolder<*>>(){


    inner class MainViewHolder(itemView: View): BaseViewHolder<List<UserEntity>>(itemView){
        override fun bind(item: List<UserEntity>, position: Int) {
            itemView.findViewById<TextView>(R.id.userName).text = item[position].name
            itemView.findViewById<TextView>(R.id.userEmail).text = item[position].email
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_users, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is UserAdapter.MainViewHolder -> holder.bind(list, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}