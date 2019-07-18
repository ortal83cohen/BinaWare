package com.cohen.binaware.viewmodel

import androidx.lifecycle.*
import com.cohen.binaware.data.ChipData
import com.cohen.binaware.dummy.DummyContent
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.room.Persistent


class TicketViewModel(private val persistent: Persistent) : ViewModel() {

    private val chipsData = MutableLiveData<ChipData?>(null)
    val tickets = liveData { emitSource(persistent.liveTickets()) }
    var addNewTicketType = MutableLiveData<Ticket.TicketType?>(null)
    var viewTicket = MutableLiveData<Ticket?>(null)
    var filter = MutableLiveData("")
    var filteredTickets = MediatorLiveData<List<Ticket>>()

    init {

        filteredTickets.addSource(tickets) {
            filterList()
        }
        filteredTickets.addSource(filter) { filter ->
            filterList()
        }

    }

    private fun filterList() {
        if (filter != null && tickets.value != null) {
            val list = ArrayList<Ticket>()
            list.addAll(tickets.value as ArrayList)
            list.addAll(DummyContent.ITEMS)
            filteredTickets.value = list.filter {
                it.title?.toLowerCase()?.startsWith(filter.value?.toLowerCase() ?: "") ?: true
            }
        }
    }

    fun setSelectedVewTicket(ticket: Ticket) {
        viewTicket.postValue(ticket)
    }

    fun setSelectedTicketType(ticketType: Ticket.TicketType?) {
        addNewTicketType.postValue(ticketType)
    }

    fun getChipsData(): LiveData<ChipData?> {
        return chipsData

    }

    fun setupChipsData(ticketType: Ticket.TicketType) {

        when (ticketType) {
            Ticket.TicketType.SERVICE -> {

            }
            Ticket.TicketType.PARTS -> {

            }
            Ticket.TicketType.URGENT -> {

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
        persistent.addOrUpdateTicket(ticket)
    }

    fun setFilter(string: String) {
        filter.postValue(string)
    }

}
