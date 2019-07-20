package com.cohen.binaware.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cohen.binaware.R
import com.cohen.binaware.data.ChipData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.chips_selection_item_list_content.view.*


class ChipsRecyclerViewAdapter(
    private val parentActivity: MainActivity,
    private val chipSelected: (Int,ChipData) -> Any,
    private val chipUnselected: (Int) -> Any
) :
    RecyclerView.Adapter<ChipsRecyclerViewAdapter.ViewHolder>() {
    var values: ArrayList<ChipData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chips_selection_item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.subListName
        holder.chipGroup.apply {
            removeAllViews()
            setOnCheckedChangeListener { chipGroup, id ->
                chipGroup?.apply {
                    if (id == -1) {
                        chipUnselected.invoke(position)
                    } else {
                        for (i in 0 until childCount) {
                            val chip = getChildAt(i) as Chip
                            if(chip.id == id) {
                                chipSelected.invoke(position, chip.tag as ChipData)
                            }
                        }
                    }
                }
            }
        }
        item.subList?.forEach {
            val chip = Chip(parentActivity)
            chip.text = it.name
            chip.tag = it
            chip.isClickable = true
            chip.isCheckable = true
            chip.isCloseIconVisible = false
            chip.chipBackgroundColor =
                parentActivity.resources.getColorStateList(R.color.chip_color)
            holder.chipGroup.addView(chip)
        }

    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val chipGroup: ChipGroup = view.chipGroup
    }
}