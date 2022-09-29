package com.example.ejemplosbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
        val airplaneMode=intent?.getBooleanExtra("state",false)
        airplaneMode?.let{
            val message = if (it) "Modo Avion Activado" else "Modo Avion Desactivado"
            binding.txtModoAvion.text=message
        }
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {

        super.onStart()
        // registrar el BroadcastRecivier
        registerReceiver(getAirplaneMode, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }


    override fun onStop() {

        super.onStop()
        //para quitar el registro
        unregisterReceiver(getAirplaneMode)
        //:)
    }

}