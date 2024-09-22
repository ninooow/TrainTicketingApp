package com.example.app

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.app.databinding.ActivityMainBinding
import com.example.app.databinding.KonfirmasiPemesananBinding
import java.util.Calendar

class MainActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private lateinit var binding:ActivityMainBinding
    private lateinit var station : Array<String>
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_TIME = "extra_time"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_STATION = "extra_station"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        station = resources.getStringArray(R.array.stasiun)

        with(binding){
            edtJam.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }

            edtDate.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "datePicker")
            }

            val adapterStation = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,station)
            edtSpinnerDestination.adapter = adapterStation
            btnRegister.setOnClickListener{
                val bundle = Bundle()
                bundle.putString(EXTRA_NAME,edtName.text.toString())
                bundle.putString(EXTRA_TIME, edtJam.text.toString())
                bundle.putString(EXTRA_DATE, edtDate.text.toString())
                bundle.putString(EXTRA_STATION, edtSpinnerDestination.selectedItem.toString())

                val dialog = Confirmation()
                dialog.arguments= bundle
                dialog.show(supportFragmentManager, "konfirmasiPemesanan")
            }

        }
    }

    override fun onTimeSet(p0: android.widget.TimePicker?, hour: Int, minute: Int) {
        val selectedTime = String.format("%02d:%02d", hour, minute)
        binding.edtJam.setText(selectedTime)


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val selectedDate = "$day/${month + 1}/$year"
        binding.edtDate.setText(selectedDate)

    }
}

class TimePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireActivity(),
            R.style.SpinnerTimePickerDialog,
            activity as TimePickerDialog.OnTimeSetListener,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }
}

class DatePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireActivity(),
            activity as DatePickerDialog.OnDateSetListener,
            year,
            monthOfYear,
            dayOfMonth
        )
    }
}

class Confirmation : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val binding = KonfirmasiPemesananBinding.inflate(inflater)
        with(binding){
            btnBuy.setOnClickListener {

                val name = arguments?.getString(MainActivity.EXTRA_NAME)
                val time = arguments?.getString(MainActivity.EXTRA_TIME)
                val date = arguments?.getString(MainActivity.EXTRA_DATE)
                val station = arguments?.getString(MainActivity.EXTRA_STATION)

                val intentToHomepage = Intent(requireActivity(), Homepage::class.java)
                intentToHomepage.putExtra(MainActivity.EXTRA_NAME,name)
                intentToHomepage.putExtra(MainActivity.EXTRA_TIME,time)
                intentToHomepage.putExtra(MainActivity.EXTRA_DATE,date)
                intentToHomepage.putExtra(MainActivity.EXTRA_STATION,station)

                startActivity(intentToHomepage)
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
        builder.setView(binding.root)
        return builder.create()
    }
}
