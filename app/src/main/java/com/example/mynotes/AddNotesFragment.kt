package com.example.mynotes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.mynotes.data.Notes
import com.example.mynotes.data.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class AddNotesFragment1 : Fragment() {
    @InternalCoroutinesApi
    lateinit var mViewModel:NotesViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel= ViewModelProvider(this).get(NotesViewModel::class.java)

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_add_notes, container, false)

        val saveBtn = view.findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            addNotesToDB()
        }

        return view
    }


    @InternalCoroutinesApi
    private fun addNotesToDB() {
        val notes = view?.findViewById<EditText>(R.id.notes)?.text.toString()

        println("ddddddddddddddddddddddddddddddd$notes")
val date = System.currentTimeMillis()

        if(inputCheck(notes)){
            val notes =Notes(0,date,notes)
            mViewModel.addNotes(notes)
            Toast.makeText(requireContext(), "Notes Saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNotesFragment1_to_listFragment)
            
        }
        else{
            Toast.makeText(requireContext(), "Please add your notes", Toast.LENGTH_SHORT).show()
        }
    }

     private fun inputCheck(notes: String): Boolean {
        return !TextUtils.isEmpty(notes)

    }


}