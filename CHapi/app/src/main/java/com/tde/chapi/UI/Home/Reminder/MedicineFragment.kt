package com.tde.chapi.UI.Home.Reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentMedicineBinding
import com.tde.chapi.model.response.GetRemindResponse
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelRemind
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MedicineFragment : Fragment() {
    private lateinit var binding: FragmentMedicineBinding
    private val viewModel: ViewModelRemind by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicine, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.textViewUsernameMedicine.text = GlobalVariables.username

        binding.radioGroupUnit.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            viewModel.unit.postValue(radio.text.toString())
        })

        binding.btnActionNextType2.setOnClickListener {
            val medicineName = binding.editTextMedicine.text.toString()
            val amountMedicine = binding.editTextNumberAmount.text.toString()


            val navController = findNavController()

            if(medicineName.isNotEmpty() && amountMedicine.isNotEmpty()){
                navController.navigate(R.id.action_medicineFragment_to_medicineFrequencyFragment)
            } else {
                Toast.makeText(activity,"No dejar campos vacios", Toast.LENGTH_SHORT).show();
            }
        }

        binding.btnActionBackMedicine.setOnClickListener {
            val navController = findNavController()

            navController.popBackStack()
        }


    }

    private fun onChekedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio){

        }
        /*binding.radioGroupUnit.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = group.findViewById(checkedId)
                if (null != radio.text) {
                    viewModel.type.postValue(radio.text.toString())
                }
            })*/
    }

}