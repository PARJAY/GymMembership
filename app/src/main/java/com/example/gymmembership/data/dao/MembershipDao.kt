package com.example.gymmembership.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gymmembership.data.model.Membership
import kotlinx.coroutines.flow.Flow

// Scheme: Get all data first, then filter in ViewModel
@Dao
interface MembershipDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMembership(membership: Membership): Long

    @Query("SELECT * FROM memberships")
    fun getAllMemberships(): Flow<List<Membership>>

    @Update
    suspend fun updateMembership(membership: Membership)

    @Delete
    suspend fun deleteMembership(membership: Membership)

    @Query("SELECT * FROM memberships WHERE memberId = :memberId")
    fun getMembershipById(memberId: Int): Flow<Membership?>

    @Query("SELECT * FROM memberships ORDER BY expiryDate ASC")
    fun getAllMembershipsSortedByExpiryDate(): Flow<List<Membership>>
}