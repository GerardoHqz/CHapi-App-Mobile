package com.tde.chapi.UI.Home.Appoinment

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
import com.tde.chapi.UI.Home.Appoinment.recycleView.ConsultationAdapter
import com.tde.chapi.UI.Home.Reminder.recycleView.RemindsAdapter
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentHomeAppointmentBinding
import com.tde.chapi.model.response.*
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeAppointmentFragment : Fragment() {
    private lateinit var binding : FragmentHomeAppointmentBinding
    private lateinit var adapter : ConsultationAdapter
    private val consult = mutableListOf<Consultation>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_appointment, container, false)
        getConsultation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initReclyrView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsernameApmnt.text = GlobalVariables.username

        //to home
        binding.btnActionHomeApmnt.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_homeAppointmentFragment_to_viewHomeFragment)
        }

        //to reminder
        binding.btnActionReminderApmnt.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_homeAppointmentFragment_to_addReminderFragment)
        }

        // to emergency
        binding.btnActionEmergencyApmnt.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_homeAppointmentFragment_to_emergencyFragment)
        }

        //to add appointment
        binding.btnActionAddappointment.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_homeAppointmentFragment_to_addAppointmentFragment1)
        }
    }

    private fun initReclyrView(){
        adapter = ConsultationAdapter(consult, {consult -> onConsultationSelected(consult) }, {consult -> onConsultationSelectedSwitch(consult) })
        binding.recyclerViewAppointment.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAppointment.adapter = adapter
    }

    private fun onConsultationSelected(consult: Consultation){
        val idConsult = consult._id
        deleteConsultation(idConsult)
    }

    private fun onConsultationSelectedSwitch(consult: Consultation){
        val idConsult = consult._id
        toogleConsultation(idConsult)
    }

    private fun deleteConsultation(idConsult : String){
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().deleteConsultation(token, idConsult)
        apiCall.enqueue(object: Callback<DeleteConsultResponse>{
            override fun onFailure(call: Call<DeleteConsultResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DeleteConsultResponse>, response: Response<DeleteConsultResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Cita eliminada!", Toast.LENGTH_SHORT).show()
                    //eliminar de la lista
                    consult.remove(consult.find { it._id == idConsult })
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun toogleConsultation(idConsult: String){
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().toogleConsultation(token, idConsult)
        apiCall.enqueue(object: Callback<ToogleConsultResponse>{
            override fun onFailure(call: Call<ToogleConsultResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ToogleConsultResponse>, response: Response<ToogleConsultResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "listo!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getConsultation(){
        val token = "Bearer ${GlobalVariables.token}"
        val user = GlobalVariables.username
        val apiCall = ApiClient.getApiServices().getConsultations(token, user)

        apiCall.enqueue(object : Callback<GetConsultResponse> {
            override fun onFailure(call: Call<GetConsultResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<GetConsultResponse>,
                response: Response<GetConsultResponse>
            ) {
                if (response.isSuccessful) {
                    val consultation = response.body()
                    if (consultation != null) {
                        consult.clear()
                        consult.addAll(consultation.consultations)
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