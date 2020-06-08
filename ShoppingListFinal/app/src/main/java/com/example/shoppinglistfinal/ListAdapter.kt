package com.example.shoppinglistfinal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val listOfItems: List<ListItem>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var onItemLongClick: ((item: ListItem) -> Unit)? = null

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_view_1
        var cardView: CardView = itemView.item_card_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listOfItems[position]

        holder.textView1.text = currentItem.text1

        holder.cardView.setOnLongClickListener {

            onItemLongClick?.invoke(currentItem)

            true
        }

    }

    override fun getItemCount() = listOfItems.size
}