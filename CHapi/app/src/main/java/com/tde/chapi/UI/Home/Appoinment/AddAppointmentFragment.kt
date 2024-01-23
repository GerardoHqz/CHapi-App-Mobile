package com.tde.chapi.UI.Home.Appoinment

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.UI.Home.Reminder.Alarm.*
import com.tde.chapi.UI.Home.Reminder.Alarm.Notification
import com.tde.chapi.UI.Home.Reminder.DatePickerFragment
import com.tde.chapi.UI.Home.Reminder.TimePickerFragment
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentAddAppointmentBinding
import com.tde.chapi.model.request.CreateConsultRequest
import com.tde.chapi.model.response.CreateConsultResponse
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelConsultation
import com.tde.chapi.viewmodel.ViewModelRemind
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class AddAppointmentFragment : Fragment() {
    private lateinit var binding : FragmentAddAppointmentBinding
    private val viewModel: ViewModelConsultation by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_appointment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChannel()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.textViewUsername.text = GlobalVariables.username

        //back to appointment fragment 1
        binding.btnActionBackToAddAppointment.setOnClickListener{
            val navController = findNavController()

            navController.popBackStack()
        }

        //back to home
        binding.btnActionContinueToAddappointment.setOnClickListener{
            getInputs()
        }

        binding.editTextHour.setOnClickListener{showTimePickerDialog()}
        binding.editTextDate.setOnClickListener{showDatePickerDialog()}
    }
    //Date Picker

    private fun showDatePickerDialog() {
        val datePicker = DatePickerAFragment { day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int) {
        binding.editTextDate.setText("$day/$month/$year")
        viewModel.date.postValue("$day/$month/$year")
    }

    //Time picker
    private fun showTimePickerDialog(){
        val timePicker = TimePickerAFragment {onTimeSelected(it)}
        timePicker.show(childFragmentManager,"time")
    }

    private fun onTimeSelected(time:String){
        binding.editTextHour.setText("$time")
        viewModel.hour.postValue("$time")
    }

    private fun getInputs(){
        val hospital = viewModel.hospital.value
        val direction = viewModel.direction.value
        val medical = viewModel.medical.value
        val date = viewModel.date.value
        val hour = viewModel.hour.value
        val activation = true
        val user = GlobalVariables.username

        if (hospital != null && direction != null && medical != null && date != null && hour != null){
            CreateAppointment(hospital, direction, medical, date, hour, activation, user)
        }
    }

    private fun CreateAppointment(hospital:String, direction:String, medical:String, date:String, hour:String, activation:Boolean, user:String){
        val request = CreateConsultRequest(hospital, direction, medical, date, hour, activation, user)
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().createConsultation(token, request)
        apiCall.enqueue(object : Callback<CreateConsultResponse> {
            override fun onFailure(call: Call<CreateConsultResponse>, t: Throwable) {
                Toast.makeText(context, "Error al crear el recordatorio", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error al crear el recordatorio", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${hospital}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${direction}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${medical}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${date}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${hour}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${activation}", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Error: ${user}", Toast.LENGTH_LONG).show()
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<CreateConsultResponse>, response: Response<CreateConsultResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Recordatorio creado", Toast.LENGTH_LONG).show()
                    val navController = findNavController()
                    navController.popBackStack()
                    navController.popBackStack()
                    scheduleNotification(hospital, direction, date, hour)
                }
                else{
                    Toast.makeText(context, "Error al crear el recordatorio", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${hospital}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${direction}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${medical}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${date}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${hour}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${activation}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Error: ${user}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showAlert(time: Long, title: String, message: String )
    {
        val hour = viewModel.completeHour.value
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

        AlertDialog.Builder(context)
            .setTitle("¡Alarma creada con exito!")
            .setMessage(
                title + message)
            .setPositiveButton("Entendido"){_,_ ->}
            .show()

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification(medical:String, direction: String, date:String, hour:String) {
        val intent = Intent(context, Notification::class.java)
        val title = "${medical} "
        val message = "${direction} ${date} ${hour}"
        intent.putExtra(titleExtra,title)
        intent.putExtra(messageExtra,message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(hour)

        alarmManager.setExactAndAllowWhileIdle(

            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

        showAlert(time, title, message)
    }

    private fun getTime(time: String): Long
    {
        val calendar = Calendar.getInstance()

        val timeSplit= time.split(":");
        val hour = timeSplit[0].toInt();
        val minutes = timeSplit[1].toInt();


        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)


        calendar.set(year, month, day, hour, minutes)

        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(channelID,name,importance)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        channel.description = desc
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}