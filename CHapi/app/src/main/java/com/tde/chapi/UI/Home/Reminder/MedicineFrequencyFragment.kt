package com.tde.chapi.UI.Home.Reminder

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tde.chapi.R
import com.tde.chapi.UI.Home.Reminder.Alarm.*
import com.tde.chapi.UI.Home.Reminder.Alarm.Notification
import com.tde.chapi.client.ApiClient
import com.tde.chapi.databinding.FragmentMedicineFrequencyBinding
import com.tde.chapi.model.request.CreateRemindRequest
import com.tde.chapi.model.response.CreateRemindResponse
import com.tde.chapi.viewmodel.GlobalVariables
import com.tde.chapi.viewmodel.ViewModelRemind
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MedicineFrequencyFragment : Fragment() {
    private lateinit var binding : FragmentMedicineFrequencyBinding
    private val viewModel: ViewModelRemind by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicine_frequency, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChannel()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.textViewUsernameFrequency.text = GlobalVariables.username

        binding.btnActionSavereminder.setOnClickListener {
            //obteniendo los datos de frecuencia

            val time = binding.numberPicker.value.toString()
            val typeTime = binding.numberPickerType.value.toString()

            viewModel.frecuently.postValue("Cada $time $typeTime")
            getInputs()
            /*if(time.length < 0 && typeTime.length < 0) {
                getInputs()
            } else {
                Toast.makeText(activity,"No dejar campos vacios", Toast.LENGTH_SHORT).show();
            }*/


        }

        binding.btnActionBackFrequency.setOnClickListener {
            val navController = findNavController()

            navController.popBackStack()
        }

        binding.editTextTime.setOnClickListener{showTimePickerDialog()}
        binding.editTextDate.setOnClickListener{showDatePickerDialog()}

        //frecuencia

        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 24

        var type = ""

        val str = arrayOf<String>("Días", "Horas", "Semana")
        binding.numberPickerType.minValue = 0
        binding.numberPickerType.maxValue = (str.size - 1)
        binding.numberPickerType.displayedValues = str
    }

    //Date Picker

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int) {
        binding.editTextDate.setText("$day/$month/$year")
        viewModel.date.postValue("$day/$month/$year")
    }

    //Time picker
    private fun showTimePickerDialog(){
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(childFragmentManager,"time")

    }

    private fun onTimeSelected(time:String){
        binding.editTextTime.setText("$time")
        viewModel.completeHour.postValue("$time")
    }

    //------LLAMADA API--------
    private fun getInputs(){
        //onFrecuentlySelected()

        val time = binding.numberPicker.value


        val typeTime = binding.numberPickerType.value.toString()
        var timeFrequency = ""

        if (typeTime == "0"){
            timeFrequency = "dias"
        }else if (typeTime == "1"){
            timeFrequency = "horas"
        }else if (typeTime == "2"){
            timeFrequency = "semanas"
        }

        val name = viewModel.name.value
        val many = viewModel.many.value
        val unit = viewModel.unit.value
        val type = viewModel.type.value
        val date = viewModel.date.value
        val hour = viewModel.completeHour.value
        val frequency = "Cada $time $timeFrequency"
        val activation = true
        val username = GlobalVariables.username

        if (name != null && many != null && unit != null && type != null && date != null && hour != null && frequency != null){
            createReminder(name, many.toInt(), unit, type, date, hour, frequency, activation, username)
        }
    }

    private fun createReminder(name:String, many:Number, unit:String, type:String, date:String, hour:String, frequency:String, activation:Boolean, username:String){
        val request = CreateRemindRequest(name, many, unit, type, date, hour, frequency, activation, username)
        val token = "Bearer ${GlobalVariables.token}"

        val apiCall = ApiClient.getApiServices().createRemind(token, request)
        apiCall.enqueue(object: Callback<CreateRemindResponse>{
            override fun onFailure(call: Call<CreateRemindResponse>, t: Throwable) {
                Toast.makeText(context, "Error al crear el recordatorio", Toast.LENGTH_LONG).show()
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<CreateRemindResponse>, response: Response<CreateRemindResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Recordatorio creado", Toast.LENGTH_LONG).show()

                    val navController = findNavController()
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.popBackStack()
                    scheduleNotification(name, date, hour, frequency, many, unit)
                }
                else{
                    Toast.makeText(context, "Error al crear el recordatorio", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, name, Toast.LENGTH_LONG).show() //si
                    Toast.makeText(context, many.toString(), Toast.LENGTH_LONG).show() //si
                    Toast.makeText(context, unit, Toast.LENGTH_LONG).show() //no
                    Toast.makeText(context, type, Toast.LENGTH_LONG).show() //si
                    Toast.makeText(context, date, Toast.LENGTH_LONG).show() //si
                    Toast.makeText(context, hour, Toast.LENGTH_LONG).show() //si
                    Toast.makeText(context, frequency, Toast.LENGTH_LONG).show() //si
                }
            }
        })
    }

    private fun showAlert(time: Long, title: String, message: String )
    {
        AlertDialog.Builder(context)
            .setTitle("¡Alarma creada con exito!")
            .setMessage(
                title + message)
            .setPositiveButton("Entendido"){_,_ ->}
            .show()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification(name:String, date:String, hour:String, frequency:String, many:Number, unit:String) {
        val intent = Intent(context, Notification::class.java)
        val title = "${name} "
        val message = "${many} ${unit} ${frequency}"
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
        val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}
