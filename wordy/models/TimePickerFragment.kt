package com.example.wordy.models

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.lang.Exception


class TimePickerFragment : DialogFragment() {
    private lateinit var mActivity: Activity
    private lateinit var mListener: TimePickerDialog.OnTimeSetListener

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (context != null) {
            mActivity=activity
        }

        try {
            mListener=activity as TimePickerDialog.OnTimeSetListener
        } catch (e: Exception) {
            print("BOBOBOBOBOBO")
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(mActivity, mListener, hour, minute, DateFormat.is24HourFormat(activity))
    }
}