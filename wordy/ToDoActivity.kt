package com.example.wordy

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordy.models.Constraint
import com.example.wordy.models.ConstraintAdapter
import com.example.wordy.models.DatePickerFragment
import com.example.wordy.models.TimePickerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*
import java.sql.Time
import kotlin.collections.ArrayList

class ToDoActivity : AppCompatActivity() {
    private var pickedHour = 0
    private var pickedMinute = 0
    private var pickedDay = 0
    private var pickedMonth = 0
    private var pickedYear = 0

    @RequiresApi(Build.VERSION_CODES.N)
    val cal = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup)

        val taskIcon: ImageView = findViewById(R.id.taskIcon)
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, "pozycja: $position", Toast.LENGTH_LONG).show()
                when (position) {
                    0 -> taskIcon.setImageResource(R.drawable.home)
                    1 -> taskIcon.setImageResource(R.drawable.work)
                    2 -> taskIcon.setImageResource(R.drawable.account)
                    3 -> taskIcon.setImageResource(R.drawable.shopping)
                    4 -> taskIcon.setImageResource(R.drawable.build)
                    5 -> taskIcon.setImageResource(R.drawable.lightbulb)
                    6 -> taskIcon.setImageResource(R.drawable.favourite)
                }
            }

        }

        val setTime: Button = findViewById(R.id.setTime)
        setTime.setOnClickListener{
            getTime(setTime, this)
        }

        val setDate: Button = findViewById(R.id.setDate)
        setDate.setOnClickListener{
            getDate(setDate, this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getTime(button: Button, context: Context){
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            pickedHour = SimpleDateFormat("HH").format(cal.time).toInt()
            pickedMinute = SimpleDateFormat("MM").format(cal.time).toInt()
            Toast.makeText(applicationContext, "hours: $pickedHour minutes: $pickedMinute", Toast.LENGTH_LONG).show()
        }

        button.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getDate(button: Button, context: Context){
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            pickedDay = SimpleDateFormat("dd").format(cal.time).toInt()
            pickedMonth = SimpleDateFormat("MM").format(cal.time).toInt()
            pickedYear = SimpleDateFormat("yyyy").format(cal.time).toInt()
            Toast.makeText(applicationContext, "day: $pickedDay month: $pickedMonth year: $pickedYear", Toast.LENGTH_LONG).show()
        }

        button.setOnClickListener {
            DatePickerDialog(context, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()        }
    }
}