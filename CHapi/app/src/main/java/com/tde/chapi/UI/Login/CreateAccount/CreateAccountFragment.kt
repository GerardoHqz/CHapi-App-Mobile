package com.tde.chapi.UI.Login.CreateAccount

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
import com.tde.chapi.databinding.FragmentCreateAccountBinding
import com.tde.chapi.model.request.RegisterRequest
import com.tde.chapi.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateAccountFragment : Fragment() {
    private lateinit var binding : FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnActionConfirmationCA.setOnClickListener{
            getInputs()
        }

        binding.btnActionBack.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }

    }

    private fun validateInputs() : Boolean {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextCreatepasswordTextinput.text.toString()
        val confirmPassword = binding.editTextConfirmCreatepasswordTextinput.text.toString()
        val username = binding.editTextCreateusername.text.toString().trim()
        val phone = binding.editTextPhone.text.toString().trim()
        var isValid = true
        if(email.isEmpty()){
            binding.editTextEmail.error = "Campo vacio"
            isValid = false
        }
        if (email.contains("@") == false){
            binding.editTextEmail.error = "Email invalido"
            isValid = false
        }
        if(password.isEmpty()){
            binding.editTextCreatepasswordTextinput.error = "Campo vacio"
            isValid = false
        }
        if(password.length <= 4){
            binding.editTextCreatepasswordTextinput.error = "Tu contraseña debe tener al menos 5 caracteres"
            isValid = false
        }
        if(confirmPassword.isEmpty()){
            binding.editTextConfirmCreatepasswordTextinput.error = "Campo vacio"
            isValid = false
        }
        if(confirmPassword != password){
            binding.editTextConfirmCreatepasswordTextinput.error = "Contraseñas no coinciden"
            isValid = false
        }
        if(username.isEmpty()){
            binding.editTextCreateusername.error = "Campo vacio"
            isValid = false
        }
        if(username.length < 4){
            binding.editTextCreateusername.error = "El nombre de usuario debe tener al menos 5 caracteres"
            isValid = false
        }
        if(phone.isEmpty()){
            binding.editTextPhone.error = "Campo vacio"
            isValid = false
        }
        if(phone.length < 8 || phone.length > 9){
            binding.editTextPhone.error = "Numero de telefono inválido"
            isValid = false
        }

        return isValid
    }

    private fun getInputs(){
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextCreatepasswordTextinput.text.toString()
        val confirmPassword = binding.editTextConfirmCreatepasswordTextinput.text.toString()
        val username = binding.editTextCreateusername.text.toString()
        val phone = binding.editTextPhone.text.toString()
        val role = "user"

        if(validateInputs()) {
            registerUser(username, email, password, phone, role)
        }
        else {
            Toast.makeText(context, "E: Campos obligatorios vacios!", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun registerUser(username: String, email: String, password: String, phone: String, role: String) {
        val registerRequest = RegisterRequest(username, email, password, phone, role)

        val apiCall = ApiClient.getApiServices().register(registerRequest)
        apiCall.enqueue(object: Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>){
                if(response.isSuccessful){
                    val navController = findNavController()

                    navController.navigate(R.id.action_createAccountFragment_to_confirmationAccountFragment)
                }
                else{
                    Toast.makeText(context, "E: ${response.message()}", Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(context, "E: ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }


}
