package com.cohen.binaware.room

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BaseTypeConverter {
    companion object {

        fun <T> fromString(value: String, type: TypeToken<T>): T {
            return Gson().fromJson(value, type.type)
        }

        fun <T> fromObject(o: T): String {
            val gson = Gson()
            return gson.toJson(o)
        }
    }
}
