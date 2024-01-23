package com.tde.chapi.UI.Home.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tde.chapi.R
import com.tde.chapi.UI.Home.Reminder.recycleView.MedicineHistoryAdapter
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentViewHistoryBinding
import com.tde.chapi.model.response.GetRemindResponse
import com.tde.chapi.model.response.Remind
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewHistoryFragment : Fragment() {
    private lateinit var binding : FragmentViewHistoryBinding
    private val reminders = mutableListOf<Remind>()
    private lateinit var adapter: MedicineHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getMedicine();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_history, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initReclyrView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsername2.text = GlobalVariables.username

        binding.btnActionBackHistoryToHome.setOnClickListener {
            val navController = findNavController()

            navController.popBackStack()
        }
    }

    private fun initReclyrView(){
        adapter = MedicineHistoryAdapter(reminders)
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewHistory.adapter = adapter
    }


    private fun getMedicine() {
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().getReminds(token)
        apiCall.enqueue(object : Callback<GetRemindResponse> {
            override fun onFailure(call: Call<GetRemindResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<GetRemindResponse>,
                response: Response<GetRemindResponse>
            ) {
                if (response.isSuccessful) {
                    val reminds = response.body()
                    if (reminds != null) {
                        reminders.clear()
                        reminders.addAll(reminds.reminds)
                        adapter.notifyDataSetChanged()
                    }
                }
                else{
                    Toast.makeText(context, "No conection", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }



}