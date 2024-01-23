package com.tde.chapi.UI.Home.Reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tde.chapi.R
import com.tde.chapi.UI.Home.Reminder.recycleView.RemindsAdapter
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentAddReminderBinding
import com.tde.chapi.model.response.DeleteRemindResponse
import com.tde.chapi.model.response.GetRemindResponse
import com.tde.chapi.model.response.Remind
import com.tde.chapi.model.response.ToogleRemindResponse
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addReminderFragment : Fragment() {
    private lateinit var binding: FragmentAddReminderBinding
    private lateinit var adapter: RemindsAdapter
    private val reminders = mutableListOf<Remind>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_reminder, container, false)
        getReminds()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initReclyrView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsernameReminder.text = GlobalVariables.username

        //to home
        binding.btnActionHomeReminder.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_addReminderFragment_to_viewHomeFragment)
        }

        //to appointment
        binding.btnActionAppointmentReminder.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_addReminderFragment_to_homeAppointmentFragment)
        }

        //to emergency
        binding.btnActionEmergencyReminder.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_addReminderFragment_to_emergencyFragment)
        }

        //to history
        binding.btnMedicinehistory.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_addReminderFragment_to_viewHistoryFragment)
        }

        binding.btnAddreminder.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_addReminderFragment_to_typeMedicationFragment)
        }

    }

    private fun initReclyrView(){
        adapter = RemindsAdapter(reminders, {remind -> onRemindSelected(remind) },
            {remind -> onRemindSelectedSwitch(remind)})
        binding.recyclerViewReminder.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewReminder.adapter = adapter
    }

    private fun onRemindSelected(remind: Remind){
        val idRemind = remind._id
        deleteRemind(idRemind)
    }

    private fun onRemindSelectedSwitch(remind: Remind){
        val idRemind = remind._id
        toogleRemind(idRemind)
    }

    private fun deleteRemind(idRemind: String){
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().deleteRemind(token, idRemind)
        apiCall.enqueue(object: Callback<DeleteRemindResponse>{
            override fun onFailure(call: Call<DeleteRemindResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DeleteRemindResponse>, response: Response<DeleteRemindResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Recordatorio eliminado!", Toast.LENGTH_SHORT).show()
                    reminders.remove(reminders.find { it._id == idRemind })
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun toogleRemind(idRemind: String){
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().toogeRemind(token, idRemind)
        apiCall.enqueue(object: Callback<ToogleRemindResponse>{
            override fun onFailure(call: Call<ToogleRemindResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ToogleRemindResponse>, response: Response<ToogleRemindResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Listo!", Toast.LENGTH_SHORT).show()
               }
            }
        })
    }

    private fun getReminds() {
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