package com.cohen.binaware.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.cohen.binaware.helpers.room.AppDatabase
import com.cohen.binaware.helpers.room.IPersistent
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.models.UserContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class Persistent(context: Context) : IPersistent {

    private val appDatabase: AppDatabase = AppDatabase.getAppDatabase(context)


    override fun deleteALLUserRelated() {
        GlobalScope.launch {
            deleteUser()
            deleteTickets()
        }
    }

//    override suspend fun liveActiveUser(): LiveData<UserContainer> {
//        return appDatabase.userDao().liveActive
//    }

    override fun liveTickets(): LiveData<List<Ticket>> {
        return appDatabase.ticketDao().liveClusters
    }

    override suspend fun tickets(): List<Ticket> {
        return withContext(Dispatchers.IO) {
            appDatabase.ticketDao().all
        }
    }

//    override fun activeUser(): UserContainer? {
//        return appDatabase.userDao().active
//    }
//
//    override suspend fun addOrUpdateUser(user: UserContainer) {
//        appDatabase.userDao().insert(user)
//    }

    override fun addOrUpdateTicket(ticket: Ticket) {
        GlobalScope.launch {
            appDatabase.ticketDao().insert(ticket)
        }
    }

    override fun deleteUser() {
        GlobalScope.launch {
//            appDatabase.userDao().deleteAll()
        }
    }

    override fun deleteTickets() {
        GlobalScope.launch {
            appDatabase.ticketDao().deleteAll()
        }
    }

}
