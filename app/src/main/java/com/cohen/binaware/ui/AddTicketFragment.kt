package com.cohen.binaware.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.cohen.binaware.R
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.fragment_add_ticket.*
import kotlinx.android.synthetic.main.fragment_add_ticket.fab
import kotlinx.android.synthetic.main.fragment_main.menu
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddTicketFragment : Fragment() {
    private lateinit var adapter: ChipsRecyclerViewAdapter

    companion object {
        fun newInstance() = AddTicketFragment()
    }

    val ticketViewModel: TicketViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_ticket, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        menu.setOnClickListener {
            Toast.makeText(context, getString(R.string.open_menu), Toast.LENGTH_SHORT).show()
        }

        setupRecyclerView(item_list)

        activity?.let {

            ticketViewModel.getChipsData().observe(it, Observer {
                val oldSize = adapter.values.size
                adapter.values = it
                when {
                    adapter.values.size == oldSize -> {
                        //         adapter.notifyItemChanged(oldSize - 1)
                    }
                    adapter.values.size < oldSize -> {
                        adapter.notifyItemRangeRemoved(
                            adapter.values.size,
                            oldSize - adapter.values.size
                        )
                    }
                    adapter.values.size > oldSize -> {
                        adapter.notifyItemInserted(oldSize)
                    }
                }

                button?.isEnabled = it.last().subList == null

            })

            ticketViewModel.getAddNewTicketType().observe(it, Observer {
                val titleString = when (it) {
                    Ticket.TicketType.SERVICE -> {
                        getString(R.string.service_ticket)
                    }
                    Ticket.TicketType.PARTS -> {
                        getString(R.string.spare_ticket)
                    }
                    Ticket.TicketType.URGENT -> {
                        getString(R.string.urgent_ticket)
                    }
                    else -> "?"
                }

                title?.text = "${getString(R.string.add)} $titleString"
                button?.setOnClickListener {
                    val selectedChips = HashMap<String, String>()
                    var subListName = ""
                    ticketViewModel.getChipsData().value!!.forEach { chip ->
                        chip.name?.let { name ->
                            selectedChips[subListName] = name
                        }
                        subListName = chip.subListName

                    }

                    ticketViewModel.addTicket(
                        Ticket(
                            title = titleString,
                            ticketType = ticketViewModel.getAddNewTicketType().value!!,
                            subTitle = edit_text.text.toString(),
                            selectedChips = selectedChips,
                            machineIsRunning = machineIsRunningSwitch.isChecked
                        )
                    )
                    edit_text.setText("")

                }
            })
        }

        fab.setOnClickListener { view ->
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
        adapter = ChipsRecyclerViewAdapter(
            activity as MainActivity,
            ticketViewModel.chipSelected(),
            ticketViewModel.chipUnselected()
        )
        recyclerView.adapter = adapter

    }
}



