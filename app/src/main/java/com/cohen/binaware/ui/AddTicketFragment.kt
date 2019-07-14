package com.cohen.binaware.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.AddTicketViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_add_ticket.*
import kotlinx.android.synthetic.main.fragment_main.menu

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementReturnTransition  = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        addTicketViewModel =
            ViewModelProviders.of(activity as MainActivity).get(AddTicketViewModel::class.java)
        menu.setOnClickListener {
            Toast.makeText(context, "open menu", Toast.LENGTH_SHORT).show()
        }

        addTicketViewModel.selectedTicketType.observe(activity as MainActivity, Observer {

            when (it) {
                AddTicketViewModel.TicketType.SERVICE -> {
                    title?.text = "Add service ticket"
                }
                AddTicketViewModel.TicketType.PARTS -> {
                    title?.text = "Add spare parts ticket"
                }
                AddTicketViewModel.TicketType.URGENT -> {
                    title?.text = "Add urgent ticket"
                }

            }


            val chips = ArrayList<String>()

            chips.add("1111111111111")
            chips.add("22222222222222")
            chips.add("33333333333333")
            chipGroup?.apply {
                removeAllViews()
                chips.forEach {
                    val chip = Chip(context)
                    chip.text = it
                    chip.isClickable = true
                    chip.isCheckable = true
                    chip.isCloseIconVisible = false
                    chip.chipBackgroundColor = resources.getColorStateList(R.color.chip_color)
                    addView(chip)
                }
            }

        })


    }


}
