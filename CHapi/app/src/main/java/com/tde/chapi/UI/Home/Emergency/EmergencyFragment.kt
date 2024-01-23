package com.tde.chapi.UI.Home.Emergency

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentEmergencyBinding
import com.tde.chapi.model.response.Emergency
import com.tde.chapi.model.response.GetEmergencyResponse
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest


class EmergencyFragment : Fragment() {
    private lateinit var binding: FragmentEmergencyBinding
    private var emergency = mutableListOf<Emergency>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emergency, container, false)

        getEmergencyphone()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsernameConfiguration.text = GlobalVariables.username

        //to home
        binding.btnActionHomeEmergency.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_emergencyFragment_to_viewHomeFragment)
        }

        //to reminder
        binding.btnActionReminderEmergency.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_emergencyFragment_to_addReminderFragment)
        }

        //to appointment
        binding.btnActionAppointmentEmergency.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_emergencyFragment_to_homeAppointmentFragment)
        }

        //to config
        binding.btnConfigurationtext.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_emergencyFragment_to_configurationFragment)
        }

        binding.btnConfiguration.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_emergencyFragment_to_configurationFragment)
        }

        binding.btnEmergencycall.setOnClickListener {
            val phone = GlobalVariables.phonenumber
            if (phone.isNotEmpty()) {
                callEmergency()
            } else {
                Toast.makeText(context, "No hay numero de emergencia", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun callEmergency() {
        val number = GlobalVariables.phonenumber

        if (number.isEmpty()){
           Toast.makeText(context, "No hay numero de emergencia", Toast.LENGTH_SHORT).show();
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$number")
            startActivity(callIntent)
        }
    }

    private fun getEmergencyphone() {
        val token = "Bearer ${GlobalVariables.token}"
        val user = GlobalVariables.username

        val apiCall = ApiClient.getApiServices().getEmergencyphone(token, user)
        apiCall.enqueue(object : Callback<GetEmergencyResponse>{
            override fun onResponse(
                call: Call<GetEmergencyResponse>,
                response: Response<GetEmergencyResponse>
            ) {
                if (response.isSuccessful){
                    val contact = response.body()

                    if (contact != null) {
                        if (contact.emergencies[0].phone != null){
                            GlobalVariables.phonenumber = contact.emergencies[0].phone
                        }
                        else{
                            Toast.makeText(context, "No hay numero de emergencia", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<GetEmergencyResponse>, t: Throwable) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


}