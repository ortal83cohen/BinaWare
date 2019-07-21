package com.cohen.binaware.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cohen.binaware.R
import com.cohen.binaware.room.Persistent
import com.cohen.binaware.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    val persistent: Persistent   by inject()
    val ticketViewModel: TicketViewModel by viewModel()

    private val addTicketFragment =
        if (supportFragmentManager.findFragmentByTag("AddTicketFragment") != null) {
            supportFragmentManager.findFragmentByTag("AddTicketFragment") as AddTicketFragment
        } else {
            AddTicketFragment.newInstance()
        }

    private val viewTicketFragment =
        if (supportFragmentManager.findFragmentByTag("ItemDetailFragment") != null) {
            supportFragmentManager.findFragmentByTag("ItemDetailFragment") as ViewTicketFragment
        } else {
            ViewTicketFragment.newInstance()
        }

    private val mainFragment by lazy {
        if (supportFragmentManager.findFragmentByTag("MainFragment") != null) {
            supportFragmentManager.findFragmentByTag("MainFragment") as MainFragment
        } else {
            MainFragment.newInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMainMode()

        ticketViewModel.getAddNewTicketType().observe(this, Observer {
            it?.let {
                ticketViewModel.setupChipsData(it)
                setAddTicketMode()
            } ?: kotlin.run {
                // setMainMode()
            }

        })

        ticketViewModel.getViewTicket().observe(this, Observer {
            it?.let {
                setViewTicketMode()
            } ?: kotlin.run {
                // setMainMode()
            }

        })

        ticketViewModel.tickets.observe(this, Observer {
            setMainMode()
        })

    }


    private fun setMainMode() {
        val transaction = supportFragmentManager.beginTransaction()
//        if (addTicketFragment.isVisible) {
//            addTicketFragment.sharedElementReturnTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
//            mainFragment.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
//            transaction.addSharedElement(addTicketFragment.fab, addTicketFragment.fab.transitionName)
//        }

        transaction.replace(R.id.fragment_container, mainFragment, "mainFragment")
        transaction.addToBackStack("mainFragment")
        transaction.commit()
    }

    private fun setAddTicketMode() {
        mainFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        addTicketFragment.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        addTicketFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        mainFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.addSharedElement(mainFragment.fab, mainFragment.fab.transitionName)
        transaction.replace(R.id.fragment_container, addTicketFragment, "addTicketFragment")
        transaction.addToBackStack("addTicketFragment")
        transaction.commit()
    }

    private fun setViewTicketMode() {
        mainFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        viewTicketFragment.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        viewTicketFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        mainFragment.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        val transaction = supportFragmentManager.beginTransaction()
        mainFragment.fab.show()
        transaction.addSharedElement(mainFragment.fab, mainFragment.fab.transitionName)
        transaction.addSharedElement(mainFragment.toolbar_layout, mainFragment.toolbar_layout.transitionName)
        transaction.addSharedElement(mainFragment.menu, mainFragment.menu.transitionName)
        transaction.replace(R.id.fragment_container, viewTicketFragment, "viewTicketFragment")
        transaction.addToBackStack("viewTicketFragment")
        transaction.commit()
    }

    override fun onBackPressed() {

        when {
            mainFragment.isVisible -> finish()
            addTicketFragment.isVisible -> ticketViewModel.setSelectedTicketType(null)
        }
        super.onBackPressed()
    }
}

