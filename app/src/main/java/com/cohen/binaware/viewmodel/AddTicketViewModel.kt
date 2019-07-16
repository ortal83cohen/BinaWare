package com.cohen.binaware.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cohen.binaware.data.ChipData
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.room.Persistent

class AddTicketViewModel() : ViewModel() {

    private lateinit var persistent: Persistent
   
    private val chipsData = MutableLiveData<ChipData?>()

//    private val database: AppDatabase = Controller

    fun setSelectedTicketType(ticketType: TicketType?) {
        selectedTicketType.postValue(ticketType)
    }


    var selectedTicketType = MutableLiveData<TicketType?>()


    enum class TicketType {
        SERVICE, PARTS, URGENT
    }


    fun getChipsData(): LiveData<ChipData?> {
        return chipsData

    }

    fun setupChipsData(ticketType: TicketType) {

        when (ticketType) {
            TicketType.SERVICE -> {

            }
            TicketType.PARTS -> {

            }
            TicketType.URGENT -> {

            }

        }
        val partTypeSubList = ArrayList<ChipData>()
        partTypeSubList.add(ChipData("Bolt", "", null, ChipData.TierNumber.FOURTH))
        partTypeSubList.add(ChipData("Bearing", "", null, ChipData.TierNumber.FOURTH))
        partTypeSubList.add(ChipData("Screw", "", null, ChipData.TierNumber.FOURTH))
        partTypeSubList.add(ChipData("Screen", "", null, ChipData.TierNumber.FOURTH))

        val toolTypeSubList = ArrayList<ChipData>()
        toolTypeSubList.add(ChipData("CW176", "", null, ChipData.TierNumber.FOURTH))
        toolTypeSubList.add(ChipData("CW876", "", null, ChipData.TierNumber.FOURTH))
        toolTypeSubList.add(ChipData("CWB79", "", null, ChipData.TierNumber.FOURTH))
        toolTypeSubList.add(ChipData("Unknown", "", null, ChipData.TierNumber.FOURTH))

        val motorTypeSubList = ArrayList<ChipData>()
        motorTypeSubList.add(ChipData("Bolt", "", null, ChipData.TierNumber.FOURTH))
        motorTypeSubList.add(ChipData("Bearing", "", null, ChipData.TierNumber.FOURTH))
        motorTypeSubList.add(ChipData("Screw", "", null, ChipData.TierNumber.FOURTH))
        motorTypeSubList.add(ChipData("Screen", "", null, ChipData.TierNumber.FOURTH))


        val beltTypeSubList = ArrayList<ChipData>()
        beltTypeSubList.add(ChipData("Smooth belt", "", null, ChipData.TierNumber.FOURTH))
        beltTypeSubList.add(ChipData("Traction belt", "", null, ChipData.TierNumber.FOURTH))
        beltTypeSubList.add(ChipData("Motor belt", "", null, ChipData.TierNumber.FOURTH))
        beltTypeSubList.add(ChipData("Connective belt", "", null, ChipData.TierNumber.FOURTH))


        val typeSubList = ArrayList<ChipData>()

        typeSubList.add(ChipData("Part", "Part type", partTypeSubList, ChipData.TierNumber.THIRD))
        typeSubList.add(ChipData("Tool", "Tool type", toolTypeSubList, ChipData.TierNumber.THIRD))
        typeSubList.add(
            ChipData(
                "Motor",
                "Motor type",
                motorTypeSubList,
                ChipData.TierNumber.THIRD
            )
        )
        typeSubList.add(ChipData("Belt", "Belt Type", beltTypeSubList, ChipData.TierNumber.THIRD))

        val reasonsSubList = ArrayList<ChipData>()

        reasonsSubList.add(ChipData("Broken", "Type", typeSubList, ChipData.TierNumber.SECOND))
        reasonsSubList.add(ChipData("Missing", "Type", typeSubList, ChipData.TierNumber.SECOND))
        reasonsSubList.add(ChipData("Reason #3", "Type", typeSubList, ChipData.TierNumber.SECOND))

        chipsData.postValue(ChipData(null, "reasons", reasonsSubList, ChipData.TierNumber.FIRST))


    }

    fun addTicket(ticket: Ticket) {
        persistent
    }

    fun init(persistent: Persistent) {
        this.persistent = persistent
    }
}
