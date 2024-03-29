package com.tde.chapi.UI.Home.Appoinment

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.tde.chapi.R
import java.util.*

class TimePickerAFragment(val listener: (String) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, R.style.Picker,this, hour, minute, false)
        return dialog


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener("$hourOfDay:$minute")
    }
}