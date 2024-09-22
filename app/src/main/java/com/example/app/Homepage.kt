package com.example.app

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.databinding.ActivityHomepageBinding

class Homepage : AppCompatActivity() {
    private lateinit var binding : ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            val getName = intent.getStringExtra(MainActivity.EXTRA_NAME)
            val getTime = intent.getStringExtra(MainActivity.EXTRA_TIME)
            val getDate = intent.getStringExtra(MainActivity.EXTRA_DATE)
            val getStation = intent.getStringExtra(MainActivity.EXTRA_STATION)
            userName.text = Html.fromHtml("Nama&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color='#525BFF'>$getName</font>", Html.FROM_HTML_MODE_LEGACY)
            userTime.text = Html.fromHtml("Jam&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color='#525BFF'>$getTime</font>", Html.FROM_HTML_MODE_LEGACY)
            userDate.text = Html.fromHtml("Tanggal&nbsp;&nbsp;:&nbsp;<font color='#525BFF'>$getDate</font>", Html.FROM_HTML_MODE_LEGACY)
            userDestination.text = Html.fromHtml("Tujuan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color='#525BFF'>$getStation</font>", Html.FROM_HTML_MODE_LEGACY)
        }
    }
}