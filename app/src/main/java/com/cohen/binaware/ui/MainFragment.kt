package com.cohen.binaware.ui

import android.animation.ValueAnimator
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.cohen.binaware.R
import com.cohen.binaware.dummy.DummyContent
import com.cohen.binaware.helpers.hideKeyboard
import com.cohen.binaware.viewmodel.AddTicketViewModel
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var addTicketViewModel: AddTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addTicketViewModel = ViewModelProviders.of(activity as MainActivity).get(AddTicketViewModel::class.java)
//        setSupportActionBar(toolbar)
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
                fab.setImageDrawable(AnimatedVectorDrawableCompat.create(context!!, R.drawable.plus_vector))
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
                fab.setImageDrawable(AnimatedVectorDrawableCompat.create(context!!, R.drawable.minus_vector))
                val drawable = fab.drawable
                if (drawable != null && drawable is Animatable) {
                    drawable.start()
                }
            }
        }

        menu.setOnClickListener {
            Toast.makeText(context, "open menu", Toast.LENGTH_SHORT).show()
        }

        setupRecyclerView(item_list)


        b1.setOnClickListener { addTicketViewModel.setSelectedTicketType(AddTicketViewModel.TicketType.SERVICE) }
        b2.setOnClickListener { addTicketViewModel.setSelectedTicketType(AddTicketViewModel.TicketType.PARTS) }
        b3.setOnClickListener { addTicketViewModel.setSelectedTicketType(AddTicketViewModel.TicketType.URGENT) }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(activity as MainActivity, DummyContent.ITEMS, false)
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
