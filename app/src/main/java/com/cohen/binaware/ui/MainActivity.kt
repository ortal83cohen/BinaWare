package com.cohen.binaware.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.AddTicketViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var addTicketViewModel: AddTicketViewModel

    private val addTicketFragment by lazy {
        if (supportFragmentManager.findFragmentByTag("AddTicketFragment") != null) {
            supportFragmentManager.findFragmentByTag("AddTicketFragment") as AddTicketFragment
        } else {
            AddTicketFragment.newInstance()
        }
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
        addTicketViewModel = ViewModelProviders.of(this).get(AddTicketViewModel::class.java)

        setMainMode()

        addTicketViewModel.selectedTicketType.observe(this, Observer {
            it?.let {
                setAddTicketMode()
            } ?: kotlin.run {

            }

        })

    }


    private fun setMainMode() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, mainFragment, "mainFragment")
        transaction.addToBackStack("mainFragment")
        transaction.commit()
    }

    private fun setAddTicketMode() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, addTicketFragment, "addTicketFragment")
        transaction.addToBackStack("addTicketFragment")
        transaction.commit()
    }

    override fun onBackPressed() {

        when {
            mainFragment.isVisible -> finish()
            addTicketFragment.isVisible -> addTicketViewModel.setSelectedTicketType(null)
        }
        super.onBackPressed()
    }

}

