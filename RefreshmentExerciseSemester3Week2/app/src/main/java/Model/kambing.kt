package Model

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.refreshmentexercisesemester3week1.R
import com.example.refreshmentexercisesemester3week1.home

class kambing(nama: String, jenis: String, usia: Int, imageuri: Uri?, makanan: String) : animalPARENT(nama, jenis, usia,
    imageuri, makanan) {



    override fun interaksi(): String {
        return "MBEKKKKK"
    }


}