package com.example.ejemplosbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.ejemplosbroadcast.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {


    private lateinit var tts: TextToSpeech

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
    // Configurar un BroadcastReceiver para el evento de cambio de tiempo a este se le denomina
    // Time Tick
    private val getTimeChange = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var a="El tiempo a cambiado"
            binding.txtTimeTick.text="El tiempo ha cambiado"

            tts.speak(a, TextToSpeech.QUEUE_FLUSH,null,"")

            }
    }
    //vamos a configurar el BroadcastReciver
    //para saber cuando el wifi esta habilitado o no
    private val getWifiMode= object: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            //Wifi trabaja pasando a traves de un registro en el
            //Intent un dato de tipo numerico ese dato
            // te va a indicar aspectos  como si  esta habilitado,
            // si no esta habilitado, configuraciones de la red,
            // si el servicio esta dañado o no se reconoce
            // Servicio Wifi: se gestiona a traves de un controlador
            // que es una clase que lo administra -> WifiManager
            val wifiMode= intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN)
            wifiMode?.let {
                val message= when(it){
                    WifiManager.WIFI_STATE_ENABLED->"Wifi habilitado"
                    WifiManager.WIFI_STATE_DISABLED->"Wifi esta desabilitado"
                    WifiManager.WIFI_STATE_UNKNOWN->"Servicio Wifi error"
                    else->"No dispone de Wifi, actualizate"

                }
                binding.txtWifi.text= message

            }
        }
    }

    override fun onInit(status: Int) {
        var resultado = if (status == TextToSpeech.SUCCESS) {
            //se puede escribir 500 lineas de codigo pero la ultima tiene que ser el
            //valor que quieres que asuma esa varianle
            //por defecto el lenguaje es ingles...
            //si quieres modificar el lenguaje se hace esto: tts.language= Locale.US
            //tts.language= Locale.US
            tts.language = Locale("ES")
            "Estado funcional correcto"
        } else "Algo salio mal,pruebe despues"

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
        //ya funciona jajaja
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this,this)
    }

    override fun onStart() {
        super.onStart()
        // Registrar el BroadcastReceiver
        registerReceiver(getAirplaneMode, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(getTimeChange, IntentFilter(Intent.ACTION_TIME_TICK))
        registerReceiver(getWifiMode, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
    }

    override fun onStop() {
        super.onStop()
        // Quitar el registro
        unregisterReceiver(getAirplaneMode)
        unregisterReceiver(getTimeChange)
    }

}


