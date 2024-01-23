package com.tde.chapi.UI.Home.History.RecycleView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.databinding.ItemBoxBinding
import com.tde.chapi.databinding.ReminderBoxBinding
import com.tde.chapi.model.response.Remind

class MedicineHistoryViewHolder (view : View): RecyclerView.ViewHolder(view) {
    private val binding = ItemBoxBinding.bind(view)

    fun bind(remind : Remind) {
        binding.textViewMedicineName.text = remind.name
        binding.textViewTypeMedicine.text = remind.type
        binding.textViewAmountMedicine.text = "${remind.many.toString()} ${remind.unit}"

    }
}