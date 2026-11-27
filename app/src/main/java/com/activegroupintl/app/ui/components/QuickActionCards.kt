package com.activegroupintl.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.data.model.QuickAction
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.Secondary
import com.activegroupintl.app.ui.theme.SurfaceCard
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary
import com.activegroupintl.app.ui.theme.TextTertiary

@Composable
fun QuickActionsGrid(
    actions: List<QuickAction>,
    onActionClick: (QuickAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (actions.size >= 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCardItem(
                    action = actions[0],
                    shortTitle = "Counselling",
                    shortSubtitle = "1-on-1 Guidance",
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[0]) }
                )
                QuickActionCardItem(
                    action = actions[1],
                    shortTitle = "Assessment",
                    shortSubtitle = "Free Profile Audit",
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[1]) }
                )
            }
        }

        if (actions.size >= 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCardItem(
                    action = actions[2],
                    shortTitle = "IELTS & PTE",
                    shortSubtitle = "Band 7.5+ Target",
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[2]) }
                )
                QuickActionCardItem(
                    action = actions[3],
                    shortTitle = "Japan Program",
                    shortSubtitle = "Language & Work",
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[3]) }
                )
            }
        }
    }
}

@Composable
fun QuickActionCardItem(
    action: QuickAction,
    shortTitle: String,
    shortSubtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val (iconVector, iconBg, iconColor) = when (action.id) {
        "counselling" -> Triple(Icons.Default.CalendarMonth, Color(0xFFE8F5E9), Primary)
        "assessment" -> Triple(Icons.Default.CheckCircleOutline, Color(0xFFFEF3C7), Color(0xFFB45309))
        "ielts" -> Triple(Icons.Default.School, Color(0xFFE0F2FE), Secondary)
        else -> Triple(Icons.Default.Translate, Color(0xFFF3E8FF), Color(0xFF7E22CE))
    }

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = shortTitle,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = shortSubtitle,
                    color = TextSecondary,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextTertiary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
