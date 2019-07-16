package com.cohen.binaware.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cohen.binaware.models.Ticket


@Dao
interface TicketDao {

    @get:Query("SELECT * FROM ticket")
    val all: List<Ticket>

    @get:Query("SELECT * FROM ticket order by ticket.time")
    val liveClusters: LiveData<List<Ticket>>

    @get:Query("SELECT * FROM ticket LIMIT 1")
    val cluster: Ticket

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(radar: Ticket)

    @Delete
    fun delete(radar: Ticket)

    @Query("DELETE FROM ticket")
    fun deleteAll()

    @Query("SELECT * FROM ticket WHERE id=:key LIMIT 1")
    operator fun get(key: String): Ticket
}

