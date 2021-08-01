package com.example.mynotes

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotes.data.Notes
import com.example.mynotes.data.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class UpdateFragment : Fragment() {
    private val args by  navArgs<UpdateFragmentArgs>()
    @InternalCoroutinesApi
    private lateinit var mViewModel :NotesViewModel
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    (activity as AppCompatActivity).supportActionBar?.show()
    setHasOptionsMenu(true)
    }
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_update, container, false)
        mViewModel=ViewModelProvider(this).get(NotesViewModel::class.java)
        val upNotes = view.findViewById<EditText>(R.id.upNotes)
        val upBtn = view.findViewById<Button>(R.id.upBtn)
        val upTitle = view.findViewById<EditText>(R.id.upNotesTitle)
upTitle.setText(args.updateArgs.title)
            upNotes.setText(args.updateArgs.notes)
        upBtn.setOnClickListener {
            updateNotes()
        }

        return  view
    }

    @InternalCoroutinesApi
    private fun updateNotes() {
        val upnotes = view?.findViewById<EditText>(R.id.upNotes)?.text.toString()
        val upTitle = view?.findViewById<EditText>(R.id.upNotesTitle)?.text.toString()
        if(inputCheck(upnotes)){

            val upNotes = Notes(args.updateArgs.id,args.updateArgs.date,upnotes,upTitle)
            mViewModel.updateNotes(upNotes)
            Toast.makeText(requireContext(), "Notes Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)


        }else{
            Toast.makeText(requireContext(), "Please enter notes", Toast.LENGTH_SHORT).show()
        }


    }
    private fun inputCheck(notes: String): Boolean {
        return !TextUtils.isEmpty(notes)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Yes"){_,_->
                    mViewModel.delete(args.updateArgs)
                    Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
findNavController().navigate(R.id.action_updateFragment_to_listFragment)

                }
                builder.setNegativeButton("No"){_,_->

                }
                builder.setMessage("Are You Sure You Want To Delete")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}