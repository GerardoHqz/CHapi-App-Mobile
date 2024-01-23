package com.tde.chapi.UI.Home.Reminder.recycleView

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.databinding.ReminderBoxBinding
import com.tde.chapi.model.response.Consultation
import com.tde.chapi.model.response.Remind
import com.tde.chapi.viewmodel.GlobalVariables

class RemindViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val binding = ReminderBoxBinding.bind(view)

    fun bind(remind : Remind, onClickListener: (Remind) -> Unit, onClickListenerSwitch: (Remind) -> Unit) {
        binding.textViewNameMedicine.text = remind.name
        binding.textViewAmountHome.text = remind.many.toString()
        binding.textViewTimeHome.text = remind.hour
        binding.textViewFrequencyHome.text = remind.frecuently
        binding.switchReminder.isChecked = remind.activation
        binding.btnActionDeletemed.setOnClickListener{
            onClickListener(remind)
        }
        binding.switchReminder.setOnClickListener(){
            onClickListenerSwitch(remind)
        }
    }
}
