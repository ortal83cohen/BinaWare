package com.cohen.binaware.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_view_ticket.*
import kotlinx.android.synthetic.main.fragment_view_ticket.fab
import kotlinx.android.synthetic.main.fragment_view_ticket.menu
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ViewTicketFragment : Fragment() {

    val ticketViewModel: TicketViewModel by sharedViewModel()
    private lateinit var adapter: SelectedChipsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_view_ticket, container, false)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(item_list)
        activity?.let {
            ticketViewModel.getViewTicket().observe(it, Observer {
                it?.let {
                    title?.text = it.title
                    adapter.values = it.selectedChips.toList()

                }

            })
        }

        menu.setOnClickListener {
            Toast.makeText(context, "open menu", Toast.LENGTH_SHORT).show()
        }

        fab.setOnClickListener {
            if (tabsMotionLayout.progress == 0f) {
                tabsMotionLayout.transitionToEnd()
                fab.setImageDrawable(
                    AnimatedVectorDrawableCompat.create(
                        context!!,
                        R.drawable.plus_vector
                    )
                )
                val drawable = fab.drawable
                if (drawable != null && drawable is Animatable) {
                    drawable.start()
                }
            } else {
                tabsMotionLayout.transitionToStart()
                fab.setImageDrawable(
                    AnimatedVectorDrawableCompat.create(
                        context!!,
                        R.drawable.minus_vector
                    )
                )
                val drawable = fab.drawable
                if (drawable != null && drawable is Animatable) {
                    drawable.start()
                }
            }

        }


    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = SelectedChipsRecyclerViewAdapter(
            activity as MainActivity
        )
        recyclerView.adapter = adapter

    }

    companion object {
        fun newInstance() = ViewTicketFragment()
    }

}
