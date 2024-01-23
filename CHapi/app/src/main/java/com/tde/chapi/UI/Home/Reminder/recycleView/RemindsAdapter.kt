package com.tde.chapi.UI.Home.Reminder.recycleView

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tde.chapi.R
import com.tde.chapi.model.response.Consultation
import com.tde.chapi.model.response.Remind

class RemindsAdapter (val remind: List<Remind>, val onClickListener: (Remind) -> Unit,
                      val onClickListenerSwitch: (Remind) ->Unit): RecyclerView.Adapter<RemindViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RemindViewHolder(layoutInflater.inflate(R.layout.reminder_box,parent,false))
    }

    override fun getItemCount(): Int {
        return remind.size
    }

    override fun onBindViewHolder(holder: RemindViewHolder, position: Int) {
        holder.bind(remind[position],onClickListener,onClickListenerSwitch)
    }
}