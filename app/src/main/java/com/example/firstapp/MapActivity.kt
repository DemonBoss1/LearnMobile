package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapp.databinding.ActivityMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("8e68e3bf-421b-4ae6-ac02-7d71e41c9c36")
        MapKitFactory.initialize(this)
        bindingClass = ActivityMapBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.mapView.map.move(
                CameraPosition(
                        Point(56.333939, 43.970847),
                        18.5f,
                        -50.0f,
                        45.0f
                )
        )
    }

    override fun onStart() {
        bindingClass.mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
    override fun onStop() {
        bindingClass.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}