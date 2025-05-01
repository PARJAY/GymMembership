package com.example.gymmembership.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymmembership.data.LocalDateConverter
import com.example.gymmembership.data.dao.MembershipDao
import com.example.gymmembership.data.model.Membership

@TypeConverters(LocalDateConverter::class)
@Database(
    entities = [Membership::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun membershipDao(): MembershipDao
}