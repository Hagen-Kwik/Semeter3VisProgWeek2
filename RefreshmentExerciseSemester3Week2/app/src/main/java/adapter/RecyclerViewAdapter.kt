package adapter

import Interface.CardListener
import Model.animalPARENT
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.refreshmentexercisesemester3week1.R
import com.example.refreshmentexercisesemester3week1.home
import com.example.refreshmentexercisesemester3week1.masukin_data_activity
import kotlinx.android.synthetic.main.cardview_foranimal.view.*
import java.io.File

class RecyclerViewAdapter(val listanimal: ArrayList<animalPARENT>, val cardListener: CardListener):
    RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator.inflate(R.layout.cardview_foranimal, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        holder.setData(listanimal[position])
    }

    override fun getItemCount(): Int {
        return listanimal.size
    }


    class viewHolder(itemView: View, val cardListener: CardListener) :
        RecyclerView.ViewHolder(itemView) {

        val edit = itemView.editbutton
        val delete = itemView.delete
        val eat = itemView.eat
        val speak = itemView.speak

        val name_card = itemView.namee
        val type_card = itemView.typee
        val age_card = itemView.agee
        val picture_card = itemView.imageforcardview


        fun setData(data: animalPARENT) {

            name_card.text = data.nama
            age_card.text = data.usia.toString()
            type_card.text = data.jenis

            if (data.imageuri != null) {
                Log.d("HI",data.nama)

                Log.d("HI",data.imageuri.toString())
                picture_card.setImageURI(data.imageuri)
            } else {
                picture_card.setImageResource(R.drawable.animalfarmlogo)
            }

            edit.setOnClickListener {
                cardListener.editCLICKED(adapterPosition)
            }

            delete.setOnClickListener{
                cardListener.deleteCLICKED(adapterPosition)
            }

            eat.setOnClickListener{
                cardListener.interactCLICKED(adapterPosition)
            }

            speak.setOnClickListener{
                cardListener.speakCLICKED(adapterPosition)
            }

        }
    }
}

