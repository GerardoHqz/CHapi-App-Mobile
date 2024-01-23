package com.tde.chapi.UI.Login.ChangePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentChangePassword2Binding
import com.tde.chapi.model.request.UpdatePassRequest
import com.tde.chapi.model.response.UpdatePassResponse
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelForgotPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChangePassword2Fragment : Fragment() {
    private lateinit var binding : FragmentChangePassword2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password2, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner


        //to login page
        binding.btnActionConfirm.setOnClickListener{
            getInputs()
        }

        //to change password page
        binding.btnActionBackToChangePassword.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }
    }

    fun getInputs(){
        val password = binding.editTextNewpasswordEdit.text.toString()
        val confirmPassword = binding.editTextConfirmNewpasswordEdit.text.toString()

        if(password != confirmPassword){
            binding.editTextConfirmNewpassword.error = "La constraseña no coincide"
            binding.editTextConfirmNewpasswordEdit.error = "La contraseña no coincide"
        }
        else{
            updatePassword(password)
        }
    }

    fun updatePassword(password: String) {
        val updatePassRequest = UpdatePassRequest(password)
        val digitPass = GlobalVariables.codeForgotPassword

        val apiCall = ApiClient.getApiServices().updatePassword(updatePassRequest,digitPass)
        apiCall.enqueue(object : Callback<UpdatePassResponse> {
            override fun onResponse(call: Call<UpdatePassResponse>, response: Response<UpdatePassResponse>) {
                if (response.isSuccessful) {
                    val updatePassResponse = response.body()
                    Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_LONG).show()
                    val navController = findNavController()
                    navController.popBackStack()
                    navController.popBackStack()

                } else {
                    Toast.makeText(context, "Error al actualizar contraseña", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<UpdatePassResponse>, t: Throwable) {
                Toast.makeText(context, "Error al actualizar contraseña", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}