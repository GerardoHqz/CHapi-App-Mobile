package com.tde.chapi.UI.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.CHapiApplication.Companion.prefs
import com.tde.chapi.R
import com.tde.chapi.databinding.FragmentLoginBinding
import com.tde.chapi.UI.Home.HomeActivity
import com.tde.chapi.client.ApiClient
import com.tde.chapi.model.request.LoginRequest
import com.tde.chapi.model.response.LoginResponse
import com.tde.chapi.viewmodel.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        checkToken()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        //to create a new account
        binding.btnActionCreateaccount.setOnClickListener{
            val navController = findNavController()

            navController.navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

        //to login
        binding.btnLogin.setOnClickListener{
            getInputs();
        }

        //to forgot password
        binding.btnActionForgotpassword.setOnClickListener{
            val navController = findNavController()
            
            navController.navigate(R.id.action_loginFragment_to_changePassword1Fragment)
        }

        //to terms
        binding.btnActionTermsncondition.setOnClickListener {
            val navController = findNavController()

            navController.navigate(R.id.action_loginFragment_to_termsFragment)
        }
    }

    private fun checkToken(){
        if(prefs.getToken().isNotEmpty()) {
            GlobalVariables.token = prefs.getToken()
            GlobalVariables.username = prefs.getName()
            moveToHome()
        }
    }

    private fun getInputs() {
        val username = binding.editTextUsername.text.toString().trim();
        val password = binding.textInputPassword.editText?.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()) {
            loginUser(username, password);
        }else {
            Toast.makeText(activity,"Usuario/Contraseña estan vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private fun loginUser(userName: String, password: String){

        val loginRequest = LoginRequest(userName,password);

        val apiCall = ApiClient.getApiServices().loginUsser(loginRequest);
        apiCall.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful) {
                    val loginResponse = response.body()

                    GlobalVariables.username = userName
                    GlobalVariables.token = loginResponse?.token.toString()

                    prefs.saveName(GlobalVariables.username)
                    prefs.saveToken(GlobalVariables.token)

                    moveToHome();

                }else {
                    Toast.makeText(activity,"Credenciales incorrectas!", Toast.LENGTH_SHORT).show();
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                Toast.makeText(activity,"Error!", Toast.LENGTH_SHORT).show();
            }
        })
    }

    private fun moveToHome() {
        requireActivity().run {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}