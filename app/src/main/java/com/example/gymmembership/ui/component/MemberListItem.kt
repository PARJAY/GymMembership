package com.example.gymmembership.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymmembership.data.model.Membership

@Composable
fun MemberListItem(member: Membership, onMemberClick: (Membership) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onMemberClick(member) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = member.name, style = MaterialTheme.typography.headlineSmall)
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "Exp: ${member.expiryDate ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
            Text(text = member.membershipCategory.name, style = MaterialTheme.typography.bodySmall)
        }
    }
}