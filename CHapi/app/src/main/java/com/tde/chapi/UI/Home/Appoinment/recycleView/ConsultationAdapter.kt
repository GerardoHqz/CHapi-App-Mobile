package com.tde.chapi.UI.Home.Appoinment.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.R
import com.tde.chapi.model.response.Consultation

class ConsultationAdapter(val consultation: List<Consultation>, val onClickListener: (Consultation) -> Unit
    , val onClickListenerSwitch: (Consultation) ->Unit) : RecyclerView.Adapter<ConsultationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ConsultationViewHolder(layoutInflater.inflate(R.layout.appoinment_box,parent,false))
    }

    override fun getItemCount(): Int {
        return consultation.size
    }

    override fun onBindViewHolder(holder: ConsultationViewHolder, position: Int) {
        holder.bind(consultation[position],onClickListener,onClickListenerSwitch)
    }
}