package com.cohen.binaware.helpers.room

import androidx.lifecycle.LiveData
import com.cohen.binaware.models.Ticket

interface IPersistent {

    fun deleteALLUserRelated()

//    suspend fun liveActiveUser(): LiveData<UserContainer>

    suspend fun liveTickets(): LiveData<List<Ticket>>

    suspend fun tickets(): List<Ticket>

//    fun activeUser(): UserContainer?
//
//    suspend fun addOrUpdateUser(user: UserContainer)

    fun addOrUpdateTicket(ticket: Ticket)

    fun deleteUser()

    fun deleteTickets()

}
