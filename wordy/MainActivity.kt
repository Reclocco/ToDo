package com.example.wordy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordy.models.Constraint
import com.example.wordy.models.ConstraintAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView adapter
        val constraints = ArrayList<Constraint>()

        constraintsList.adapter = ConstraintAdapter(constraints)
        constraintsList.layoutManager = LinearLayoutManager(this)
        constraintsList.setHasFixedSize(false)

        

        //add button window on click
        addButton.setOnClickListener{
            val intent = Intent(this, ToDoActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}