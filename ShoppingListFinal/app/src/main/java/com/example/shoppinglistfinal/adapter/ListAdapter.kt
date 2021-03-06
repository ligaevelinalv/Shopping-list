package com.example.shoppinglistfinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistfinal.R
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var listOfItems = listOf<ListItem>()
    var onItemLongClick: ((item: ListItem) -> Unit)? = null
    var onCheckBoxClick: ((item: ListItem) -> Unit)? = null

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_view_1
        var cardView: CardView = itemView.item_card_view
        val check: CheckBox = itemView.checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listOfItems[position]

        holder.check.isChecked = currentItem.ischecked

        holder.textView1.text = currentItem.text1

        holder.cardView.setOnLongClickListener {

            onItemLongClick?.invoke(currentItem)

            true
        }



        holder.check.setOnCheckedChangeListener { _, isChecked ->
            currentItem.ischecked = isChecked
            onCheckBoxClick?.invoke(currentItem)
        }
    }

    override fun getItemCount() = listOfItems.size
}