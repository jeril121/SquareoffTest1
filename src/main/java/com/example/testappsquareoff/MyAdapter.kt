package com.example.testappsquareoff


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val userList : ArrayList<User>,private val rotate : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
    }
    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image:ImageView=itemView.findViewById(R.id.imageportait)
        var name: TextView = itemView.findViewById(R.id.name)
        var slug: TextView=itemView.findViewById(R.id.slug)
        var year: TextView=itemView.findViewById(R.id.year)
        var dash: TextView=itemView.findViewById(R.id.ndash)
        fun bind(position: Int) {
            val recyclerViewModel = userList[position]
            Picasso.get().load(recyclerViewModel.img).into(image)
            name.text = recyclerViewModel.name
            slug.text=recyclerViewModel.slug
            year.text = recyclerViewModel.slug?.takeLast(4)
            dash.text = recyclerViewModel.slug?.filter { it == '-' }?.count().toString()
        }
    }
    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name1: TextView = itemView.findViewById(R.id.title_small)
        var image1:ImageView=itemView.findViewById(R.id.image_small)

        fun bind(position: Int) {
            val recyclerViewModel = userList[position]
            name1.text = recyclerViewModel.name
            Picasso.get().load(recyclerViewModel.img).into(image1)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (rotate == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_item_portait, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_grid, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (rotate === VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {

        return userList.size
    }
    override fun getItemViewType(position: Int): Int {
        return rotate
    }







    }


