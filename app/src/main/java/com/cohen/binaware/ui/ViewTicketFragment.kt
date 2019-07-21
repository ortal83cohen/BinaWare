package com.cohen.binaware.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.TicketViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ViewTicketFragment : Fragment() {

    val ticketViewModel: TicketViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_view_ticket, container, false)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            ticketViewModel.getViewTicket().observe(it, Observer {


            })
        }
    }

    companion object {
        fun newInstance() = ViewTicketFragment()
    }

}
