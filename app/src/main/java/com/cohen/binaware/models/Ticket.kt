package com.cohen.binaware.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cohen.binaware.room.TicketConverters
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "ticket")
@TypeConverters(TicketConverters::class)
data class Ticket constructor(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var ticketType: TicketType = TicketType.UNKNOWN,
    var title: String? = null,
    var subTitle: String? = null,
    var time: Date? = Date(),
    var selectedChips: HashMap<String, String> = HashMap(),
    var machineIsRunning: Boolean = true,
    @Ignore
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
) {
    enum class TicketType {
        SERVICE, PARTS, URGENT, UNKNOWN
    }

}

