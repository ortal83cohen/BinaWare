package com.cohen.binaware.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cohen.binaware.room.UserContainerConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
@TypeConverters(UserContainerConverters::class)
data class UserContainer(
    @SerializedName("_id") @Expose(serialize = false, deserialize = true) var id: String,
    @SerializedName("isEmailVerified") @Expose var isEmailVerified: Boolean,
    @SerializedName("user") @Expose var user: User,
//    @SerializedName("userData") @Expose var userData: UserData?,
    @SerializedName("token") @Expose var token: String
) {

    val firstName: String
        get() =
            if (user.firstName != "") {
                user.firstName
            } else {
                user.email.split("@").first()
            }

    val lastName: String?
        get() = user.lastname

    val email: String?
        get() = user.email

    val userId: String
        get() = user.id

    @SerializedName("key")
    @Expose(serialize = false, deserialize = true)
    @PrimaryKey(autoGenerate = false)
    var key: String = "TheOnlyUser"
        get() = "TheOnlyUser"

}
