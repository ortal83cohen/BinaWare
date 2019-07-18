package com.cohen.binaware.ui

import android.animation.ValueAnimator
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.cohen.binaware.R
import com.cohen.binaware.helpers.hideKeyboard
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : Fragment() {
    private lateinit var adapter: TicketsRecyclerViewAdapter
    val ticketViewModel: TicketViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener { view ->
            if (topBar.progress == 0f) {
                topBar.transitionToEnd()
                fab.requestFocus()
                hideKeyboard()
                val anim = ValueAnimator.ofInt(app_bar.measuredHeight, app_bar.measuredHeight + 400)
                anim.addUpdateListener { valueAnimator ->
                    val animatedValue = valueAnimator.animatedValue as Int
                    val layoutParams = app_bar.getLayoutParams()
                    layoutParams.height = animatedValue
                    app_bar.setLayoutParams(layoutParams)
                }
                anim.duration = 300
                anim.start()
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
                val anim = ValueAnimator.ofInt(app_bar.measuredHeight, app_bar.measuredHeight - 400)
                anim.addUpdateListener { valueAnimator ->
                    val animatedValue = valueAnimator.animatedValue as Int
                    val layoutParams = app_bar.getLayoutParams()
                    layoutParams.height = animatedValue
                    app_bar.setLayoutParams(layoutParams)
                }
                anim.duration = 300
                anim.start()
                topBar.transitionToStart()
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

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                ticketViewModel.setFilter(p0.toString())
            }

        })

        menu.setOnClickListener {
            Toast.makeText(context, "open menu", Toast.LENGTH_SHORT).show()
        }

        setupRecyclerView(item_list)

        b1.setOnClickListener { ticketViewModel.setSelectedTicketType(Ticket.TicketType.SERVICE) }
        b2.setOnClickListener { ticketViewModel.setSelectedTicketType(Ticket.TicketType.PARTS) }
        b3.setOnClickListener { ticketViewModel.setSelectedTicketType(Ticket.TicketType.URGENT) }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = TicketsRecyclerViewAdapter(activity as MainActivity) {
            ticketViewModel.setSelectedVewTicket(it)
        }
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayout.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        ticketViewModel.filteredTickets.observe(this, Observer {
            adapter.values = it
            adapter.notifyDataSetChanged()
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
