package com.example.shoppinglistfinal

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class Dialogue : DialogFragment() {

    var onPositive: () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Delete item?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    onPositive()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->  })
            // Create the AlertDialog object and return it

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
