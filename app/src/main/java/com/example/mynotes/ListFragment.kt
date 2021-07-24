package com.example.mynotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.data.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.InternalCoroutinesApi


class ListFragment : Fragment() {


@InternalCoroutinesApi
private lateinit var mNotesViewModel:NotesViewModel
  private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
recyclerView= view.findViewById(R.id.rcView)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= GridLayoutManager(requireContext(),2)

        mNotesViewModel=ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotesViewModel.readAllData.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })


        val addNotesBtn = view.findViewById<FloatingActionButton>(R.id.addNotesBtn)
        addNotesBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addNotesFragment1)

        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_grid,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
  when(item.itemId){
      R.id.list ->   recyclerView.layoutManager= LinearLayoutManager(requireContext())
R.id.grid -> recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

  }
return super.onOptionsItemSelected(item)
    }


}