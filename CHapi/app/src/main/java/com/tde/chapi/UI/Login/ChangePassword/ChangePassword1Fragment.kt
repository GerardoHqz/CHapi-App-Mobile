package com.tde.chapi.UI.Login.ChangePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentChangePassword1Binding
import com.tde.chapi.model.request.ForgotPassRequest
import com.tde.chapi.model.response.ForgotPassResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChangePassword1Fragment : Fragment() {
    private lateinit var binding: FragmentChangePassword1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password1, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnActionContinueToChangePasswordFragment2.setOnClickListener{
            getInputs()
        }

        binding.btnActionBackChangePasswordtoLogin.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }

    }

    fun getInputs(){
        val email = binding.editTextUsernameChangepswrd.text.toString()

        if(email.isNotEmpty()){
            forgotPassword(email);
        }
        else{
            binding.editTextUsernameChangepswrd.error = "Campo email vacio"
        }
    }

    fun forgotPassword(email: String){
        val forgotPassRequest = ForgotPassRequest(email)

        val apiCall = ApiClient.getApiServices().forgotPassword(forgotPassRequest)
        apiCall.enqueue(object: Callback<ForgotPassResponse>{
            override fun onResponse(call: Call<ForgotPassResponse>, response: Response<ForgotPassResponse>) {
                if(response.isSuccessful){
                    val forgotPassResponse = response.body()
                    //imprimir el mensaje de respuesta
                    Toast.makeText(getActivity(), forgotPassResponse?.message, Toast.LENGTH_LONG).show()

                    val navController = findNavController()
                    navController.navigate(R.id.action_changePassword1Fragment_to_fragmentChangePassword32)
                }
                else{
                    binding.editTextUsernameChangepswrd.error = "Correo no encontrado"
                }
            }
            override fun onFailure(call: Call<ForgotPassResponse>, t: Throwable) {
                Toast.makeText(getActivity(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }
        })
    }


}