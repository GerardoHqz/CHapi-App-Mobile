package com.tde.chapi.UI.Home.Reminder

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.tde.chapi.R
import java.util.*

class DatePickerFragment(val listener: (day:Int, month:Int, year:Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month+1, year)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance() //calendario
        val day = c.get(Calendar.DAY_OF_MONTH) //dia actual
        val month = c.get(Calendar.MONTH) //mes actual
        val year  = c.get(Calendar.YEAR) //anio actual

        //crear la instancia del calendario osea lo que vera el usuario
        val picker = DatePickerDialog(activity as Context, R.style.Picker,this, year, month, day)
        picker.datePicker.minDate = c.timeInMillis
        return picker
    }
}