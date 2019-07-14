package com.cohen.binaware.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.AddTicketViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class AddTicketFragment : Fragment() {

    companion object {
        fun newInstance() = AddTicketFragment()
    }

    private lateinit var addTicketViewModel: AddTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addTicketViewModel = ViewModelProviders.of(activity as MainActivity).get(AddTicketViewModel::class.java)
        menu.setOnClickListener {
            Toast.makeText(context, "open menu", Toast.LENGTH_SHORT).show()
        }
    }

}
