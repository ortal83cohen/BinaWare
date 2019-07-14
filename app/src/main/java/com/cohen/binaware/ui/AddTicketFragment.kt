package com.cohen.binaware.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cohen.binaware.R
import com.cohen.binaware.data.ChipData
import com.cohen.binaware.viewmodel.AddTicketViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
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

            addChipGroup(addTicketViewModel.getChipsData().value, chipGroup, reason)

        })


    }

    private fun addChipGroup(chipData: ChipData?, chipGroup: ChipGroup?, textView: TextView?) {
        chipGroup?.apply {

            removeAllChipViews(this, textView)

            chipData?.apply {
                textView?.text = subListName
                subList?.forEach {
                    val chip = Chip(context)
                    chip.text = it.name
                    chip.tag = it
                    chip.isClickable = true
                    chip.isCheckable = true
                    chip.isCloseIconVisible = false
                    chip.chipBackgroundColor = resources.getColorStateList(R.color.chip_color)
                    addView(chip)
                }
                setOnCheckedChangeListener { chipGroup, id ->
                    if (id == -1) {
                        when (chipData.tierNumber) {
                            ChipData.TierNumber.FIRST -> removeAllChipViews(chipGroup2, reason2)
                            ChipData.TierNumber.SECOND -> removeAllChipViews(chipGroup3, reason3)
                        }
                    } else {
                        for (i in 0 until childCount) {
                            val chip = getChildAt(i) as Chip
                            if (chip.id == id) {
                                if (chip.tag is ChipData) {
                                    val chipData = chip.tag as ChipData
                                    when (chipData.tierNumber) {
                                        ChipData.TierNumber.SECOND -> addChipGroup(chip.tag as ChipData, chipGroup2, reason2)
                                        ChipData.TierNumber.THIRD -> addChipGroup(chip.tag as ChipData, chipGroup3, reason3)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun removeAllChipViews(chipGroup: ChipGroup?, reason: TextView?) {
        reason?.text = ""
        chipGroup?.apply {
            getChildAt(0)?.let {
                val chip = it as Chip
                if (chip.tag is ChipData) {
                    val chipData = chip.tag as ChipData
                    when (chipData.tierNumber) {
                        ChipData.TierNumber.SECOND -> removeAllChipViews(chipGroup2, reason2)
                        ChipData.TierNumber.THIRD -> removeAllChipViews(chipGroup3, reason3)
                    }
                }
                removeAllViews()
            }
        }
    }
}



