package com.tde.chapi.UI.Home.Reminder.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.R
import com.tde.chapi.UI.Home.History.RecycleView.MedicineHistoryViewHolder
import com.tde.chapi.model.response.Remind

class MedicineHistoryAdapter (val remind: List<Remind>): RecyclerView.Adapter<MedicineHistoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MedicineHistoryViewHolder(layoutInflater.inflate(R.layout.item_box,parent,false))
    }

    override fun getItemCount(): Int {
        return remind.size
    }

    override fun onBindViewHolder(holder: MedicineHistoryViewHolder, position: Int) {
        holder.bind(remind[position])
    }
}