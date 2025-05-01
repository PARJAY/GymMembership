package com.example.gymmembership.di

import com.example.gymmembership.data.database.AppDatabase
import com.example.gymmembership.repository.MembershipRepository

interface AppModule {
    val database: AppDatabase
    val membershipRepository: MembershipRepository
}