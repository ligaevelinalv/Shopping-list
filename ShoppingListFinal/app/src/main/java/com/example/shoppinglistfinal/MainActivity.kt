package com.example.shoppinglistfinal

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
//    private var finalList: MutableList<ListItem> = generateList()

    val shoppingListViewModel by lazy {
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

        var enterButton = button as Button

        enterButton.setOnClickListener {
            insertItem(it)
        }


        adapter.onItemLongClick = {

            var dial = Dialogue()
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
        var inputField = input_field as EditText
        val newItem = ListItem(text1 = inputField.text.toString(), ischecked = false)

        shoppingListViewModel.insertItem(newItem)

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        input_field.text.clear()

    }
}