package com.cohen.binaware.room

import androidx.room.TypeConverter
import com.cohen.binaware.models.Ticket
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*


class TicketConverters : BaseTypeConverter() {

    @TypeConverter
    fun usersFromString(string: String): HashMap<String, Ticket.User> {
        return BaseTypeConverter.fromString(
            string,
            object : TypeToken<HashMap<String, Ticket.User>>() {})
    }

    @TypeConverter
    fun toString(users: HashMap<String, Ticket.User>): String {
        return BaseTypeConverter.fromObject(users)
    }

    @TypeConverter
    fun timeFromString(string: String): Date? {
        return BaseTypeConverter.fromString(string, object : TypeToken<Date?>() {})
    }

    @TypeConverter
    fun toString(time: Date?): String {
        time?.let { return BaseTypeConverter.fromObject(time) } ?: kotlin.run { return "" }
    }

    @TypeConverter
    fun simpleDateFormatFromString(string: String): SimpleDateFormat {
        return BaseTypeConverter.fromString(string, object : TypeToken<SimpleDateFormat>() {})
    }

    @TypeConverter
    fun toString(time: SimpleDateFormat): String {
        return BaseTypeConverter.fromObject(time)
    }

    @TypeConverter
    fun chipsFromString(string: String): ArrayList<String> {
        return BaseTypeConverter.fromString(string, object : TypeToken<ArrayList<String>>() {})
    }

    @TypeConverter
    fun toString(time: ArrayList<String>): String {
        return BaseTypeConverter.fromObject(time)
    }

}
