package com.cohen.binaware.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddTicketViewModel : ViewModel() {
    fun setSelectedTicketType(ticketType: TicketType?) {
        selectedTicketType.postValue(ticketType)
    }

    var selectedTicketType = MutableLiveData<TicketType?>()


    enum class TicketType {
        SERVICE, PARTS, URGENT
    }
}
