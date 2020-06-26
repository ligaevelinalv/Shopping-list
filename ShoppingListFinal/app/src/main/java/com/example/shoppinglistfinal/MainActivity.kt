package com.example.shoppinglistfinal

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistfinal.adapter.ListAdapter
import com.example.shoppinglistfinal.adapter.ListItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListAdapter

    private val shoppingListViewModel by lazy {
        ViewModelProvider(this).get(ShoppingListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shoppingListViewModel.allItems.observe(this, Observer {
            adapter.listOfItems = it
            adapter.notifyDataSetChanged()

        })

        adapter = ListAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        enter_button.setOnClickListener {
            insertItem(it)
        }


        adapter.onItemLongClick = {

            val dial = Dialogue()
            dial.show(supportFragmentManager, "")
            dial.onPositive = {
                shoppingListViewModel.deleteItem(it)
                adapter.notifyDataSetChanged()
            }
        }

        adapter.onCheckBoxClick = {
            shoppingListViewModel.updateItem(it)
        }
    }

    private fun insertItem(view: View) {
        val newItem = ListItem(
            text1 = input_field.text.toString(),
            ischecked = false
        )

        shoppingListViewModel.insertItem(newItem)

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        input_field.text.clear()

    }
}