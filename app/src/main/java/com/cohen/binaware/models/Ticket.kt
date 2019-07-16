package com.cohen.binaware.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cohen.binaware.room.TicketConverters
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "ticket")
@TypeConverters(TicketConverters::class)
data class Ticket(
    var users: HashMap<String, User> = HashMap(),
    var title: String? = null,
    var time: Date? = null,
    @PrimaryKey
    var id: String = "",
    @Ignore
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"),
    var chips: ArrayList<String> = ArrayList()
) {

    fun getDisplayTime(): String? {
        return simpleDateFormat.format(time ?: Date())
    }

    fun addUsers(u: HashMap<String, User>) {
        users.putAll(u)
    }

    fun getUsersIds(): String {
        var list = ""
        users.forEach {
            list += "${it.key}|"
        }
        return list
    }

    data class User(
        var device_token: String = "",
        var name: String? = null,
        var email: String = "",
        var userId: String = "",
        var image: String? = null,
        var headline: String? = null,
        var lastOnline: Date? = null,
        var unreadMessages: HashMap<String, Int> = HashMap(),
        var role: String = "member"
    ) {

        fun isRobot(): Boolean {
            return role == "admin" || role == "robot"
        }

        fun getFirstName(): String {
            return name?.split(" ")?.first() ?: email.split("@").first()
        }
    }
}

