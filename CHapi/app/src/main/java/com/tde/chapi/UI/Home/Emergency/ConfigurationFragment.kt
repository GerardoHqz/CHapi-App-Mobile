package com.tde.chapi.UI.Home.Emergency

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tde.chapi.CHapiApplication.Companion.prefs
import com.tde.chapi.R
import com.tde.chapi.databinding.FragmentConfigurationBinding
import com.tde.chapi.UI.Login.LoginActivity
import com.tde.chapi.client.ApiClient
import com.tde.chapi.model.request.CreateEmergencyRequest
import com.tde.chapi.model.response.CreateEmergencyResponse
import com.tde.chapi.model.response.GetEmergencyResponse
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfigurationFragment : Fragment() {
    private lateinit var binding : FragmentConfigurationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_configuration, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.viewModel = viewModel
        disableInputs()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textViewUsernameConfiguration2.text = GlobalVariables.username

        //save
        binding.btnActionSave.setOnClickListener{
            getInputs()

        }
        // back to home
        binding.btnActionCancelConfiguration.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }

        //logout
        binding.btnActionLogout.setOnClickListener{
            logout()
        }

    }

    private fun logout(){
       prefs.wipe()
        requireActivity().run {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    private fun getInputs(){
        val name = binding.editTextEmergencyname.text.toString()
        val email = binding.editTextEmailemegency.text.toString()
        val phone = binding.editTextEmergencytelephone.text.toString()
        val username = GlobalVariables.username

        if(name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()){
            createContact(name, email, phone, username)
        }
        else{
            Toast.makeText(context, "Campos obligatorios vacíos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createContact(name: String, email: String, phone: String, username: String){
        val emergencyRequest = CreateEmergencyRequest(name, email, phone, username)
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().createEmergency(token,emergencyRequest)
        apiCall.enqueue(object: Callback<CreateEmergencyResponse>{
            override fun onResponse(call: Call<CreateEmergencyResponse>, response: Response<CreateEmergencyResponse>){
                if(response.isSuccessful){
                    Toast.makeText(context, "Emergencia creada correctamente!", Toast.LENGTH_SHORT).show()
                    val navController = findNavController()
                    navController.popBackStack()
                }
                else{
                    Toast.makeText(context, "Error al crear emergencia!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<CreateEmergencyResponse>, t: Throwable){
                Toast.makeText(context, "Error al crear emergencia!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun disableInputs(){
        if (GlobalVariables.phonenumber.isNotEmpty()){

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
                            binding.editTextEmergencyname.setText(contact.emergencies[0].name)
                            binding.editTextEmergencyname.isEnabled = false

                            binding.editTextEmergencytelephone.setText(contact.emergencies[0].phone)
                            binding.editTextEmergencytelephone.isEnabled = false

                            binding.editTextEmailemegency.setText(contact.emergencies[0].email)
                            binding.editTextEmailemegency.isEnabled = false

                            binding.btnActionSave.setBackgroundColor(Color.GRAY)
                            binding.btnActionSave.isEnabled = false
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
        else{
            binding.editTextEmergencyname.isEnabled = true
            binding.editTextEmergencytelephone.isEnabled = true
            binding.editTextEmailemegency.isEnabled = true
            binding.btnActionSave.isEnabled = true
            binding.btnActionSave.setBackgroundColor(Color.parseColor("#00bcd4"))
        }
    }


}