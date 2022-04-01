package com.example.mymoviesapp.presentation.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviesapp.R
import com.example.mymoviesapp.domain.Movie
import com.example.mymoviesapp.presentation.BaseViewHolder

class MoviesAdapter(
    private val context: Context,
    private val list: List<Movie>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(list, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MainViewHolder(itemView: View) :
        BaseViewHolder<List<Movie>>(itemView) {
        override fun bind(item: List<Movie>, position: Int) {
            itemView.findViewById<TextView>(R.id.title_movie).text = item[position].title

            val url = item[position].img
            val imageMovie = itemView.findViewById<ImageView>(R.id.img_movie)

            Glide.with(context)
                .load(url)
                .into(imageMovie)
        }
    }
}