package Model

import android.net.Uri

class sapi(nama: String, jenis: String, usia: Int, imageuri: Uri?, makanan: String) : animalPARENT(nama, jenis, usia,
    imageuri, makanan) {

    override fun interaksi(): String {
        return "MOOOOOOOOO"
    }

}