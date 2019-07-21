package com.cohen.binaware.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cohen.binaware.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.chips_selection_item_list_content.view.title
import kotlinx.android.synthetic.main.selected_chip_item_list_content.view.*


class SelectedChipsRecyclerViewAdapter(
    private val parentActivity: MainActivity
) :
    RecyclerView.Adapter<SelectedChipsRecyclerViewAdapter.ViewHolder>() {
    var values: List<Pair<String, String>> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.selected_chip_item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.first
        holder.chip.text = item.second
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val chip: Chip = view.chip
    }
}