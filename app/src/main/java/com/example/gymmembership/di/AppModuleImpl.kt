package com.example.gymmembership.di

import android.content.Context
import com.example.gymmembership.data.database.AppDatabase
import androidx.room.Room
import com.example.gymmembership.data.dao.MembershipDao
import com.example.gymmembership.repository.MembershipRepository

class AppModuleImpl(private val appContext: Context) : AppModule {

    override val database: AppDatabase by lazy {
        Room.databaseBuilder(appContext, AppDatabase::class.java, "gym_membership_database")
            .fallbackToDestructiveMigration(false) // Hanya untuk pengembangan, hapus di produksi
            .build()
    }

    private val membershipDao: MembershipDao by lazy {
        database.membershipDao()
    }

    override val membershipRepository: MembershipRepository by lazy {
        MembershipRepository(membershipDao)
    }
}