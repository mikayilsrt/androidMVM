package com.app.androidmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.androidmvvm.R
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.views.movie.MovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(var popularMovies : List<Movie> = listOf()) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(row)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(movie : Movie) {
            val factory : DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide
                .with(view.context)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .transition(withCrossFade(factory))
                .transform(RoundedCorners(9))
                .into(view._moviePoster)

            view._title.text = movie.title

            view.setOnClickListener { MovieActivity.start(view.context, movie.id)  }
        }
    }
}