package com.cohen.binaware.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cohen.binaware.R
import com.cohen.binaware.models.Ticket
import kotlinx.android.synthetic.main.item_list_content.view.*

class TicketsRecyclerViewAdapter(
    private val parentActivity: MainActivity, ticketClicked: (Ticket) -> Any
) :
    RecyclerView.Adapter<TicketsRecyclerViewAdapter.ViewHolder>() {
    var values: List<Ticket> = ArrayList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            ticketClicked.invoke(v.tag as Ticket)
//                    parentActivity.supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit()

//                  J.context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.contentView.text = item.subTitle

        when (item.ticketType) {
            Ticket.TicketType.PARTS -> {
                holder.circle.text = "P"
                holder.circle.background = parentActivity.resources.getDrawable(R.drawable.circle_p)
            }
            Ticket.TicketType.SERVICE -> {
                holder.circle.text = "S"
                holder.circle.background = parentActivity.resources.getDrawable(R.drawable.circle_s)

            }
            Ticket.TicketType.URGENT -> {
                holder.circle.text = "U"
                holder.circle.background = parentActivity.resources.getDrawable(R.drawable.circle_u)

            }
        }

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title_text
        val contentView: TextView = view.content
        val circle: TextView = view.circle
    }
}