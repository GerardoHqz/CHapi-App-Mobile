package com.tde.chapi.UI.Home.Reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.databinding.FragmentTypeMedicationBinding
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelRemind

class TypeMedicationFragment : Fragment() {
    private lateinit var binding: FragmentTypeMedicationBinding
    private val viewModel: ViewModelRemind by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_type_medication, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.textViewUsernameType.text = GlobalVariables.username

        binding.btnPills.setOnClickListener{
            viewModel.type.postValue("Pildoras")
            val navController = findNavController()
            navController.navigate(R.id.action_typeMedicationFragment_to_medicineFragment)
        }

        binding.btnSyrup.setOnClickListener{
            viewModel.type.postValue("Solucion oral")
            val navController = findNavController()
            navController.navigate(R.id.action_typeMedicationFragment_to_medicineFragment)
        }

        binding.btnVaccination.setOnClickListener{
            viewModel.type.postValue("Inyeccion")
            val navController = findNavController()
            navController.navigate(R.id.action_typeMedicationFragment_to_medicineFragment)
        }

        binding.btnOthers.setOnClickListener{
            viewModel.type.postValue("Otros")
            val navController = findNavController()
            navController.navigate(R.id.action_typeMedicationFragment_to_medicineFragment)
        }

        binding.btnActionBackType.setOnClickListener{
            val navController = findNavController()
            navController.popBackStack()
        }
    }


}