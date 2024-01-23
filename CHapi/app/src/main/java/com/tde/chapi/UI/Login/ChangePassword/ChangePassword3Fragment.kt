package com.tde.chapi.UI.Login.ChangePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentChangePassword3Binding
import com.tde.chapi.model.request.VerifyCodeRequest
import com.tde.chapi.model.response.VerifyCodeResponse
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelForgotPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChangePassword3Fragment : Fragment() {
    private lateinit var binding : FragmentChangePassword3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangePassword3Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnActionContinueToChangePasswordFragment2.setOnClickListener {
            getInput()
        }

        binding.btnActionBackChangePasswordtoLogin.setOnClickListener {
            val navController = findNavController()

            navController.popBackStack();
        }
    }

    fun getInput(){
        val code = binding.editTextVerifycodeAnswer.text.toString()

        if(code.isNotEmpty()){
            VerifyCodeForgotPassword(code)
        }
        else{
            binding.editTextVerifycodeAnswer.error = "Please enter your answer"
        }
    }

    fun VerifyCodeForgotPassword(code : String){
        //convetir el code a numero
        val codeVerify = VerifyCodeRequest(code)

        val apiCall = ApiClient.getApiServices().verifyCode(codeVerify)
        apiCall.enqueue(object: Callback<VerifyCodeResponse>{
            override fun onResponse(call: Call<VerifyCodeResponse>, response: Response<VerifyCodeResponse>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        //capturando el codigo de verificacion
                            GlobalVariables.codeForgotPassword = binding.editTextVerifycodeAnswer.text.toString()
                        Toast.makeText(context, "VerifyCodeResponse: ${responseBody.message}", Toast.LENGTH_LONG).show()

                        val navController = findNavController()
                        navController.navigate(R.id.action_fragmentChangePassword32_to_changePassword2Fragment)

                    }
                    else{
                        binding.editTextVerifycodeAnswer.error = "Invalid code"
                    }
                }
                else{
                    Toast.makeText(context, "Server Error", Toast.LENGTH_LONG).show()
                }

            }
            override fun onFailure(call: Call<VerifyCodeResponse>, t: Throwable) {
                Toast.makeText(context, "VerifyCodeResponse: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}