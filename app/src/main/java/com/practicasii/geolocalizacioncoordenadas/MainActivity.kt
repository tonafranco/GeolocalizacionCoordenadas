package com.practicasii.geolocalizacioncoordenadas

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.practicasii.geolocalizacioncoordenadas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        updateLocation()
        binding.btnUpdateLocalitation.setOnClickListener{
            updateLocation()
        }
    }

    fun updateLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Doesn't have permission",Toast.LENGTH_SHORT).show()
            Log.d("LocationPermissions", "Doesn't have permission")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1)
            }
            return
        }else{
            Toast.makeText(this,"Has permission", Toast.LENGTH_SHORT).show()
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                binding.tvCordinates.setText("${location?.latitude}, ${location?.longitude}")
                Log.d("LocationPermissions", "Success ${location}")
            }
            Log.d("LocationPermissions", "Has permission ")
        }
    }






}