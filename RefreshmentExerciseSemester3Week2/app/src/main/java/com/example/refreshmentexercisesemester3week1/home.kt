package com.example.refreshmentexercisesemester3week1

import Interface.CardListener
import Model.animalPARENT
import Model.ayam
import Model.kambing
import Model.sapi
import adapter.RecyclerViewAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.cardview_foranimal.*

class home : AppCompatActivity(),CardListener {

    private val adapter = RecyclerViewAdapter(animalARRAY, this)
    private val adapterFINAL = RecyclerViewAdapter(final, this)


    companion object {
        var animalARRAY: ArrayList<animalPARENT> = ArrayList()
        var final: ArrayList<animalPARENT> = ArrayList()
        var lastclickedbutton: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()


        setupRecycler()
        listener()
    }

    override fun onResume() {
        super.onResume()

        final.clear()
        if (lastclickedbutton == "kambingbutton") {
            for (i in animalARRAY) {
                if (i is kambing) {
                    final.add(i)
                }
            }
            recyclerviewhome.adapter = adapterFINAL

        } else if (lastclickedbutton == "sapibutton") {
            for (i in animalARRAY) {
                if (i is sapi) {
                    final.add(i)
                }
            }
            recyclerviewhome.adapter = adapterFINAL

        } else if (lastclickedbutton == "ayambutton") {
            for (i in animalARRAY) {
                if (i is ayam) {
                    final.add(i)
                }
            }
            recyclerviewhome.adapter = adapterFINAL

        } else{
            recyclerviewhome.adapter = adapter
    }
        adapterFINAL.notifyDataSetChanged()
        adapter.notifyDataSetChanged()

    }

    fun listener(){
        newbutton.setOnClickListener{
            val myIntent = Intent(this, masukin_data_activity::class.java)
            startActivity(myIntent)
        }

        sapibut.setOnClickListener {
            final.clear()

            for (i in animalARRAY) {
                if (i is sapi) {
                    final.add(i)
                }
            }

            recyclerviewhome.adapter = adapterFINAL
            super.onResume()
            adapter.notifyDataSetChanged()
            adapterFINAL.notifyDataSetChanged()
            lastclickedbutton = "sapibutton"
        }

        ayambut.setOnClickListener {
            final.clear()

            for (i in animalARRAY) {
                if (i is ayam) {
                    final.add(i)
                }
            }
            lastclickedbutton = "ayambutton"
            recyclerviewhome.adapter = adapterFINAL
            super.onResume()
            adapter.notifyDataSetChanged()
            adapterFINAL.notifyDataSetChanged()

        }

        kambingbut.setOnClickListener {
            final.clear()
            for (i in animalARRAY) {
                if (i is kambing) {
                    final.add(i)
                }
            }
            lastclickedbutton = "kambingbutton"

            recyclerviewhome.adapter = adapterFINAL
            super.onResume()
            adapter.notifyDataSetChanged()
            adapterFINAL.notifyDataSetChanged()

        }

        clear.setOnClickListener {
            lastclickedbutton = ""
            recyclerviewhome.adapter = adapter
        }
    }

    fun setupRecycler(){
        recyclerviewhome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewhome.adapter = adapter

    }

    override fun editCLICKED(position: Int) {
        val myIntent = Intent(this, masukin_data_activity::class.java).apply{
            putExtra("position",position)
        }
        startActivity(myIntent)
    }

    override fun deleteCLICKED(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do You Want to Delete?")
        builder.setMessage("Once deleted it will never come back")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()

            if (lastclickedbutton == "") {
                animalARRAY.removeAt(position)

            } else {

                var objectTEMP = final[position]
                var indexTEMP = -1
                var index = 0

                for (i in animalARRAY){
                    index++
                    if(i == objectTEMP) {
                        indexTEMP = index
                        break
                    }
                }

                indexTEMP -= 1
                animalARRAY.removeAt(indexTEMP)
                final.removeAt(position)

            }
            onResume()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

    override fun speakCLICKED(position: Int) {
        if (lastclickedbutton == ""){
        Toast.makeText(this, animalARRAY[position].interaksi(), Toast.LENGTH_SHORT).show()
    } else {
            Toast.makeText(this, final[position].interaksi(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun interactCLICKED(position: Int) {
        if (lastclickedbutton == ""){

        if (animalARRAY.get(position) is ayam) {
            Toast.makeText(this, animalARRAY.get(position).memberimakan(1), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, animalARRAY.get(position).memberimakan("rumput"), Toast.LENGTH_SHORT).show()
        }

    } else {
            if (final.get(position) is ayam) {
                Toast.makeText(this, final.get(position).memberimakan(1), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, final.get(position).memberimakan("rumput"), Toast.LENGTH_SHORT).show()
            }
    }

    }
}