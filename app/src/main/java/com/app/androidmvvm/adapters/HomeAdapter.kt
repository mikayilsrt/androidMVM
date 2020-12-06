package com.app.androidmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.androidmvvm.R
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.views.movie.MovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.app.androidmvvm.databinding.MovieItemBinding

class HomeAdapter(var popularMovies : List<Movie> = listOf()) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    inner class ViewHolder(private val binding : MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            val context = binding.root.context
            val factory : DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .transition(withCrossFade(factory))
                .transform(RoundedCorners(9))
                .into(binding.MoviePoster)

            binding.movie = movie
            binding.root.setOnClickListener { MovieActivity.start(context, movie.id)  }
        }
    }
}