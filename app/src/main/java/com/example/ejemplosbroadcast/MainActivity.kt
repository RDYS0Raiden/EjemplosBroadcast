package com.example.ejemplosbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplosbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding

//definir un objeto de tipo BroadcastReciver
//dentro de este objeto directamente aplicaremos la implementacion
//o sobreescritura de un metodo clave en la
//confguracion de un Broadcast que es el metodo
//onReceive
private val getAirplaneMode=object :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val ariplaneMode=intent.getBooleanExtra("state",false)
        
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {

        super.onStart()

    }


    override fun onStop() {

        super.onStop()

    }

}