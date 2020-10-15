package com.atoming.notepadkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.atoming.notepadkotlin.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_notes_layout.*

class AllNotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.all_notes_layout, container, false).apply {
            recyclerView = findViewById(R.id.all_notes_rv)
            addBtn = findViewById(R.id.add_note_btn)
        }
        addBtn.setOnClickListener {
            this.findNavController().navigate(R.id.action_allNotesFragment_to_addLinkFragment)
        }
        //recyclerView = v.findViewById(R.id.all_notes_rv)
        return v
    }
}