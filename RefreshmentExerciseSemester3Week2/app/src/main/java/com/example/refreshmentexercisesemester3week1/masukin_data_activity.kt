package com.example.refreshmentexercisesemester3week1

import Model.animalPARENT
import Model.sapi
import Model.ayam
import Model.kambing
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_masukin_data.*
import java.io.File

class masukin_data_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masukin_data)
        supportActionBar?.hide()
        uri = null
        listener()

    }

    val REQUEST_CODE = 100

    var uri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            PICTURE.setImageURI(data?.data) // handle chosen image
            uri = data?.data!!
            Log.d("here", uri.toString())
        }
    }

    fun listener(){


        pictbutoton.setOnClickListener{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }

        back.setOnClickListener{
            finish()
        }

        val dataforedit: Int = intent.getIntExtra("position", -1)

        if (dataforedit != -1) {
            nama.getEditText()?.setText(home.animalARRAY.get(dataforedit).nama)
            usia.getEditText()?.setText(home.animalARRAY.get(dataforedit).usia.toString())
            if (home.animalARRAY.get(dataforedit).imageuri.toString().isNotEmpty()) {
                PICTURE.setImageURI(home.animalARRAY.get(dataforedit).imageuri)
            }

            var jenisss = home.animalARRAY.get(dataforedit).jenis

            if(jenisss == "Sapi"){
                sapi.isChecked = true
            } else if(jenisss == "Kambing"){
                kambing.isChecked = true
            } else if(jenisss == "Ayam"){
                ayam.isChecked = true
            }

            tambahORedit.text = "Edit Hewan"
        }


        finishbutton.setOnClickListener{
            var name = nama.editText?.text.toString().trim()
            var age = usia.editText?.text.toString().trim()
            var jenis = "";
            var makanan = ""
            if (RADIOJENIS.getCheckedRadioButtonId() == R.id.ayam){
                jenis = "Ayam"
                makanan = "biji-bijian"
            } else if(RADIOJENIS.getCheckedRadioButtonId() == R.id.sapi){
                jenis = "Sapi"
                makanan = "rerumputan"
            } else if(RADIOJENIS.getCheckedRadioButtonId() == R.id.kambing){
                jenis = "Kambing"
                makanan = "rerumputan"
            }
            var checking = true

            usia.error = ""
            nama.error = ""

            if (jenis!!.isEmpty()) {
                Toast.makeText(this, "Pilih Satu Jenis", Toast.LENGTH_SHORT).show()
                checking = false
            }

            if (name!!.isEmpty()) {
                    nama.error = "Masukan Nama dengan Benar"
                    checking = false
            }

            if (age!!.isEmpty()) {
                    usia.error = "Masukan Usia dengan Benar"
                    checking = false
            }

            if (checking) {

                Log.d("here",uri.toString())

                if (dataforedit != -1) {
                    if(uri != null) {
                        var temp = makenewclass(name, age.toInt(), uri, jenis, makanan)
                        home.animalARRAY[dataforedit] = temp
                    } else{
                        uri = home.animalARRAY[dataforedit].imageuri
                        var temp = makenewclass(name, age.toInt(), uri, jenis, makanan)
                        home.animalARRAY[dataforedit] = temp
                    }

                    finish()

                } else {
                    var temp = makenewclass(name, age.toInt(), uri, jenis, makanan)
                    home.animalARRAY.add(temp)
                    finish()
                }
            }
        }
    }

    fun makenewclass(n:String,a:Int,u:Uri?,j:String, m:String): animalPARENT {
        if(j == "Sapi"){
            return sapi(n,j,a,u,m)
        } else if(j == "Ayam"){
            return ayam(n,j,a,u,m)
        } else {
            return kambing(n,j,a,u,m)
        }

    }


}