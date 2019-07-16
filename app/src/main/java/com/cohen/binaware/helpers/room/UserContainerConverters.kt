package com.cohen.binaware.room

import android.service.autofill.UserData
import androidx.room.TypeConverter
import com.cohen.binaware.models.User

import com.google.gson.reflect.TypeToken

class UserContainerConverters : BaseTypeConverter() {

    @TypeConverter
    fun userFromString(string: String): User? {
        return BaseTypeConverter.fromString(string, object : TypeToken<User>() {

        })
    }

    @TypeConverter
    fun toString(user: User): String {
        return BaseTypeConverter.fromObject(user)
    }

    @TypeConverter
    fun userDataFromString(string: String?): UserData? {
        string?.let {
            return BaseTypeConverter.fromString(it, object : TypeToken<UserData>() {

            })
        }
        return null
    }

    @TypeConverter
    fun toString(user: UserData?): String {
        return BaseTypeConverter.fromObject(user)
    }

}
