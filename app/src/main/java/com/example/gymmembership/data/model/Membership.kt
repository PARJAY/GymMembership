package com.example.gymmembership.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

enum class MembershipCategory {
    DIBAWAH_18,
    UMUM_PRIA,
    UMUM_WANITA,
    COUPLE
}

@Entity(tableName = "memberships")
@Parcelize
data class Membership(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memberId")
    val memberId: Int = 0,

    val name: String = "",
    val joinDate: LocalDate? = LocalDate.now(),
    var expiryDate: LocalDate? = null,

    val membershipCategory: MembershipCategory = MembershipCategory.UMUM_PRIA,

    val monthlyFee: Float = 0f,
    var isActive: Boolean = true,
    val notes: String = ""
) : Parcelable {
    val registrationFee: Float
        get() = when (membershipCategory) {
            MembershipCategory.DIBAWAH_18 -> 100f
            MembershipCategory.UMUM_PRIA -> 170f
            MembershipCategory.UMUM_WANITA -> 150f
            MembershipCategory.COUPLE -> 130f
        }
}


// membershipType2 ini akan menentukan registration fee nya
// Contoh: "dibawah 18" => 100, "umum pria" => 170, "umum wanita" => 150, 'couple' => 130.