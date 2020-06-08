package com.example.shoppinglistfinal

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var finalList: MutableList<ListItem> = generateList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ListAdapter(finalList)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        var enterButton = button as Button

        enterButton.setOnClickListener {
            insertItem(it)
        }


        adapter.onItemLongClick = {

            var dial = Dialogue()
            dial.show(supportFragmentManager, "missiles")
            dial.onPositive = {
                finalList.remove(it)
                adapter.notifyDataSetChanged()

            }
            // You can show popup instead and then delete it
            // You have an item to delete 'it'

//                finalList.remove(it)

        }


    }



    private fun generateList(): MutableList<ListItem> {
        val list = ArrayList<ListItem>()
        list += ListItem("Chicken flavored ramen")
        list += ListItem("Green tea")
        list += ListItem("HDMI cable")

        return list
    }

    private fun insertItem(view: View) {
        var inputField = input_field as EditText
        val newItem = ListItem(inputField.text.toString())

        finalList.add(newItem)

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        input_field.text.clear()

    }


    fun deleteItem(position: Int) {

//        finalList.removeAt(position)

//        Log.i("error", "${finalList[position]}")

    }

}