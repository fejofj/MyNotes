package com.example.mynotes

import android.content.Context
import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.data.Notes
import org.w3c.dom.Text
import java.lang.String.format
import java.text.DateFormat
import java.text.DateFormat.MONTH_FIELD
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import kotlin.coroutines.coroutineContext

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var notesList = emptyList<Notes>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
val date = itemView.findViewById<TextView>(R.id.date)
        val notes = itemView.findViewById<TextView>(R.id.disNotes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = notesList[position]
        val date = current.date

        holder.date.text=DateFormat.getInstance().format(date)
      holder.notes.text=current.notes.take(30)

        holder.itemView.setOnClickListener{

            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(current)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(notes:List<Notes>){
        this.notesList=notes
        notifyDataSetChanged()
    }
}