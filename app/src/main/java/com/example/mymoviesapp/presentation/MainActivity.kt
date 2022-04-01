package com.example.mymoviesapp.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mymoviesapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        checkPermissions()
        checkLocationProvider()

        db.collection("locations").document().set {}

    }

    private fun checkLocationProvider() {

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        return if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Habilitar GPS")
            builder.setCancelable(false)
            builder.setMessage("Es necesario para tener un mejor resultado")
            builder.setPositiveButton("Habilitar") { dialogInterface, i ->
                startActivityForResult(
                    Intent(
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS,

                        ), REQUEST_CODE_GPS
                )
            }
            builder.create().show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_GPS -> {
                getLocationUser()
            }
        }
    }


    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) + ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                Snackbar.make(
                    this.findViewById(android.R.id.content),
                    "Habilitar servicos de Ubicacion",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("HABILITAR",
                    View.OnClickListener { v: View? -> showPermissionDialog() }).show()

            } else {
                showPermissionDialog()
            }
        } else {

            getLocationUser()
        }
    }

    private fun showPermissionDialog() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_CODE_PERMISSION
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationUser()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.msg_gps),
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun getLocationUser() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val geocoder: Geocoder = Geocoder(this, Locale.getDefault())
                    val address: List<Address> =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    val latitude = address[0].latitude
                    val longitude = address[0].longitude
                    val addressComplete = address[0].getAddressLine(0)
                    Toast.makeText(this, addressComplete, Toast.LENGTH_LONG).show()
                }

            }
    }

    companion object {
        const val REQUEST_CODE_GPS = 101
        const val REQUEST_CODE_PERMISSION = 100

    }

}