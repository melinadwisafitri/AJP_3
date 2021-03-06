package com.example.android.ajp_1.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.ajp_1.R
import com.example.android.ajp_1.entity.remote.response.MovieResponse
import com.example.android.ajp_1.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.item_list.view.*

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    private val listSearch = ArrayList<MovieResponse>()

    fun selectedSearch(item: List<MovieResponse>?){
        if (item == null) return
        listSearch.clear()
        notifyDataSetChanged()
        listSearch.addAll(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(listSearch[position])
    }

    override fun getItemCount(): Int = listSearch.size

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieResponse) {
            with(itemView){
                tv_title.text = movie.title
                tv_desc.text = movie.description
                tv_director.text = movie.rilis
                ratingbar.rating = movie.userScore.toFloat()

                setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_POSTER, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movie.imgPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh))
                    .error(R.drawable.ic_baseline_broken_image)
                    .into(img_poster)
                tv_userscore.text = movie.userScore.toString()
            }
        }

    }
}