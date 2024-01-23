package com.tde.chapi.UI.Home.Appoinment

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
import com.tde.chapi.databinding.FragmentAddAppointment1Binding
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelConsultation


class AddAppointmentFragment1 : Fragment() {
    private lateinit var binding : FragmentAddAppointment1Binding
    private val viewModel : ViewModelConsultation by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_appointment1, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.textViewUsername.text = GlobalVariables.username

        //to appointment fragment 2
        binding.btnActionContinueToAddappointment.setOnClickListener{

            val place = binding.editTextPlace.text.toString()
            val address = binding.editTextAddress.toString()
            val navController = findNavController()

            if (place.isNotEmpty() && address.isNotEmpty()) {
                navController.navigate(R.id.action_addAppointmentFragment1_to_addAppointmentFragment)
            } else {
                Toast.makeText(activity,"No dejar campos vacios", Toast.LENGTH_SHORT).show();
            }

        }

        //back to home fragment
        binding.btnActionBackAddAppointmentToHome.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }
    }
}