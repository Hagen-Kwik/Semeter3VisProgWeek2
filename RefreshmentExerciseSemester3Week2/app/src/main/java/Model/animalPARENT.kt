package Model

import android.net.Uri

open class animalPARENT(
    var nama:String,
    var jenis:String,
    var usia:Int,
    var imageuri: Uri?,
    var makanan:String) {

    var name:String = nama
    var type:String = jenis
    var age:Int = usia
    var imageuriii:Uri? = imageuri
    var food: String = makanan

    fun memberimakan(s: String): String {
        return "Kamu memberi makan hewan dengan rumput"
    }

    fun <Int> memberimakan(i: Int): String {
        return "Kamu memberi makan hewan dengan biji bijian"
    }

    open fun interaksi():String {
        return ""
    }
}