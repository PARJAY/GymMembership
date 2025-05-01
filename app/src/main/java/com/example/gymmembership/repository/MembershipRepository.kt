package com.example.gymmembership.repository

import com.example.gymmembership.data.dao.MembershipDao
import com.example.gymmembership.data.model.Membership
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.withContext

class MembershipRepository(private val membershipDao: MembershipDao) {
    // Secara default, kita akan mengambil semua membership dan membiarkan ViewModel memfilternya
    val allMemberships: Flow<List<Membership>> = membershipDao.getAllMemberships().conflate()
    val allMembershipsSortedByExpiryDate: Flow<List<Membership>> = membershipDao.getAllMembershipsSortedByExpiryDate().conflate()

    suspend fun insertMembership(membership: Membership): Long = withContext(Dispatchers.IO) {
        membershipDao.insertMembership(membership)
    }

    suspend fun updateMembership(membership: Membership) = withContext(Dispatchers.IO) {
        membershipDao.updateMembership(membership)
    }

    suspend fun deleteMembership(membership: Membership) = withContext(Dispatchers.IO) {
        membershipDao.deleteMembership(membership)
    }

    fun getMembershipById(memberId: Int): Flow<Membership?> = membershipDao.getMembershipById(memberId).conflate()

}