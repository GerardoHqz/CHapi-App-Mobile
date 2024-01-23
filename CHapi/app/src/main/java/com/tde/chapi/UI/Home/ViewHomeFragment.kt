package com.tde.chapi.UI.Home

import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tde.chapi.R
import com.tde.chapi.UI.Home.Appoinment.recycleView.ConsultationAdapter
import com.tde.chapi.UI.Home.Reminder.recycleView.RemindsAdapter
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentViewHomeBinding
import com.tde.chapi.model.request.ConsultationsTodayRequest
import com.tde.chapi.model.request.RemindsTodayRequest
import com.tde.chapi.model.response.*
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ViewHomeFragment : Fragment() {
    private lateinit var binding : FragmentViewHomeBinding
    private lateinit var adapter : ConsultationAdapter
    private val consult = mutableListOf<Consultation>()

    private lateinit var adapterB : RemindsAdapter
    private val reminders = mutableListOf<Remind>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initReclyrView()
        initReclyrView2()

        val c = Calendar.getInstance() //calendario
        val day = c.get(Calendar.DAY_OF_MONTH) //dia actual
        val month = (c.get(Calendar.MONTH))+1//mes actual
        val year  = c.get(Calendar.YEAR) //anio actual
        val date = "$day/$month/$year" //fecha actual

        binding.textViewMedicaments.text = date

        getReminds(date)
        getConsultation(date)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsernameHome.text = GlobalVariables.username

        binding.btnActionReminderHome.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_viewHomeFragment_to_addReminderFragment)
        }

        binding.btnActionAppointmentHome.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_viewHomeFragment_to_homeAppointmentFragment)
        }

        binding.btnActionEmergencyHome.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_viewHomeFragment_to_emergencyFragment)
        }
    }

    private fun initReclyrView(){
        adapter = ConsultationAdapter(consult, {consult -> onConsultationSelected(consult) }, {consult -> onConsultationSelectedSwitch(consult) })
        binding.recyclerViewAppoinmenthome.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAppoinmenthome.adapter = adapter
    }

    private fun initReclyrView2(){
        adapterB = RemindsAdapter(reminders, {remind -> onRemindSelected(remind) },
            {remind -> onRemindSelectedSwitch(remind)})
        binding.recyclerViewReminderhome.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewReminderhome.adapter = adapterB
    }

    private fun onConsultationSelected(consult: Consultation){
        val idConsult = consult._id
        deleteConsultation(idConsult)
        adapter.notifyDataSetChanged()
    }

    private fun onConsultationSelectedSwitch(consult: Consultation){
        val idConsult = consult._id
        toogleConsultation(idConsult)
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
                    adapterB.notifyDataSetChanged()
                }
            }
        })
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
                    Toast.makeText(context, "Consulta eliminada!", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, "Listo!", Toast.LENGTH_SHORT).show()
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

    private fun getConsultation(date: String){
        val token = "Bearer ${GlobalVariables.token}"
        val user = GlobalVariables.username
        val request = ConsultationsTodayRequest(date ,user)

        val apiCall = ApiClient.getApiServices().getConsultationsToday(token, request)
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
                    if (consultation != null ) {
                        //mostrar solo consultas del dia actual
                        for (i in 0 until consultation.consultations.size){
                            if (consultation.consultations[i].date == date){
                                consult.clear()
                                consult.add(consultation.consultations[i])
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                    else{
                        Toast.makeText(context, "No hay consultas", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(context, "No conection", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun getReminds(date : String) {
        val token = "Bearer ${GlobalVariables.token}"
        val user = GlobalVariables.username
        val request = RemindsTodayRequest(date, user)

        val apiCall = ApiClient.getApiServices().getRemindsToday(token, request)
        apiCall.enqueue(object : Callback<GetRemindResponse> {
            override fun onFailure(call: Call<GetRemindResponse>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<GetRemindResponse>,
                response: Response<GetRemindResponse>
            ) {
                if (response.isSuccessful) {
                    val remind = response.body()
                    if (remind != null) {
                        //mostrar solo consultas del dia actual
                        for (i in 0 until remind.reminds.size){
                            if (remind.reminds[i].date == date){
                                reminders.clear()
                                reminders.add(remind.reminds[i])
                                adapterB.notifyDataSetChanged()
                            }
                        }
                    }
                    else{
                        Toast.makeText(context, "No conection", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(context, "No conection", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    //Obtener id de un recordatorio

}