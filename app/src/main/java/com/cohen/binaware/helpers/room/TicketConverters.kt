package com.cohen.binaware.room

import androidx.room.TypeConverter
import com.cohen.binaware.models.Ticket
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*


class TicketConverters : BaseTypeConverter() {

    @TypeConverter
    fun usersFromString(string: String): Ticket.TicketType {
        return BaseTypeConverter.fromString(
            string,
            object : TypeToken<Ticket.TicketType>() {})
    }

    @TypeConverter
    fun toString(users: Ticket.TicketType): String {
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
    @TypeConverter
    fun HashMapFromString(string: String): HashMap<String, String> {
        return BaseTypeConverter.fromString(string, object : TypeToken<HashMap<String, String>>() {})
    }

    @TypeConverter
    fun toString(time: HashMap<String, String>): String {
        return BaseTypeConverter.fromObject(time)
    }

}
