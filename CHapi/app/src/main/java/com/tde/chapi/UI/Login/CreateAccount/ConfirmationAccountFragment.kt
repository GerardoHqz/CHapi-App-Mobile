package com.tde.chapi.UI.Login.CreateAccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.databinding.FragmentConfirmationAccountBinding


class ConfirmationAccountFragment : Fragment() {
    private lateinit var binding : FragmentConfirmationAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation_account, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnActionContinue.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
            navController.popBackStack()
        }
    }

}