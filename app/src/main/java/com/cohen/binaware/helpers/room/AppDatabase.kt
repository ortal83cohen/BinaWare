package com.cohen.binaware.helpers.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cohen.binaware.models.Ticket
import com.cohen.binaware.models.UserContainer
import com.cohen.binaware.room.TicketDao
import com.cohen.binaware.room.UserDao

@Database(
    entities = [(UserContainer::class), (Ticket::class)],
    version = 1//, (UserData::class)
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun ticketDao(): TicketDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "room-database"
                )
                    .fallbackToDestructiveMigration()
//                      .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }

    }
}