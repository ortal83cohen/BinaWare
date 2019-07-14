package com.cohen.binaware.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.AddTicketViewModel

class AddTicketFragment : Fragment() {

    companion object {
        fun newInstance() = AddTicketFragment()
    }

    private lateinit var addTicketViewModel: AddTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_ticket_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addTicketViewModel = ViewModelProviders.of(activity as MainActivity).get(AddTicketViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
