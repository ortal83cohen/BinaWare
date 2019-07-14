package com.cohen.binaware.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cohen.binaware.R
import com.cohen.binaware.viewmodel.AddTicketViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var addTicketViewModel: AddTicketViewModel

    private val addTicketFragment =
        if (supportFragmentManager.findFragmentByTag("AddTicketFragment") != null) {
            supportFragmentManager.findFragmentByTag("AddTicketFragment") as AddTicketFragment
        } else {
            AddTicketFragment.newInstance()
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
        transaction.replace(com.cohen.binaware.R.id.fragment_container, mainFragment, "mainFragment")
        transaction.addToBackStack("mainFragment")
        transaction.commit()
    }

    private fun setAddTicketMode() {
        mainFragment.sharedElementReturnTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        addTicketFragment.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.addSharedElement(mainFragment.fab, mainFragment.fab.transitionName)
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
//    public class DetailsTransition : TransitionSet {
//        public DetailsTransition() {
//            setOrdering(ORDERING_TOGETHER);
//            addTransition( ChangeBounds()).
//                addTransition( ChangeTransform()).
//                addTransition( ChangeImageTransform()));
//        }
//    }
}

