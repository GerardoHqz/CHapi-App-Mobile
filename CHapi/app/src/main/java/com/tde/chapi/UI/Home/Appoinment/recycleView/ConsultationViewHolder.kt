package com.tde.chapi.UI.Home.Appoinment.recycleView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.UI.Home.Appoinment.Appointment
import com.tde.chapi.databinding.AppoinmentBoxBinding
import com.tde.chapi.model.response.Consultation

class ConsultationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = AppoinmentBoxBinding.bind(view)

    fun bind(appointment: Consultation, onClickListener: (Consultation) -> Unit, onClickListenerSwitch: (Consultation) -> Unit) {
        binding.textViewNameHospital.text = appointment.hospital
        binding.textViewPlace.text = appointment.direction
        binding.textViewMedical.text = appointment.medical
        binding.textViewDate.text = appointment.date
        binding.textViewTimeHome.text = appointment.hour
        binding.switchAppointment.isChecked = appointment.activation
        binding.btnActionDeleteappointment.setOnClickListener {
            onClickListener(appointment)
        }
        binding.switchAppointment.setOnClickListener {
            onClickListenerSwitch(appointment)
        }
    }
}