package com.activegroupintl.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.RecordVoiceOver
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
import com.activegroupintl.app.data.model.QuickActionBadgeType
import com.activegroupintl.app.ui.theme.BadgeAdmission
import com.activegroupintl.app.ui.theme.BadgeAdmissionBg
import com.activegroupintl.app.ui.theme.BadgeSuccessBg
import com.activegroupintl.app.ui.theme.BadgeWarningBg
import com.activegroupintl.app.ui.theme.BadgeWarningText
import com.activegroupintl.app.ui.theme.BorderSubtle
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.Secondary
import com.activegroupintl.app.ui.theme.SecondaryContainer
import com.activegroupintl.app.ui.theme.SurfaceCard
import com.activegroupintl.app.ui.theme.SurfaceContainer
import com.activegroupintl.app.ui.theme.Tertiary
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
        // First row of 2 cards
        if (actions.size >= 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCardItem(
                    action = actions[0],
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[0]) }
                )
                QuickActionCardItem(
                    action = actions[1],
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[1]) }
                )
            }
        }

        // Second row of 2 cards
        if (actions.size >= 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCardItem(
                    action = actions[2],
                    modifier = Modifier.weight(1f),
                    onClick = { onActionClick(actions[2]) }
                )
                QuickActionCardItem(
                    action = actions[3],
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            // Icon row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val iconVector = when (action.iconName) {
                    "calendar" -> Icons.Default.CalendarToday
                    "fact_check" -> Icons.Default.FactCheck
                    "record_voice_over" -> Icons.Default.RecordVoiceOver
                    else -> Icons.Default.Translate
                }

                val iconBgColor = when (action.badgeType) {
                    QuickActionBadgeType.PRIMARY -> BadgeAdmissionBg
                    QuickActionBadgeType.WARNING -> BadgeWarningBg
                    QuickActionBadgeType.INFO -> SurfaceContainer
                    QuickActionBadgeType.SUCCESS -> BadgeSuccessBg
                }

                val iconTint = when (action.badgeType) {
                    QuickActionBadgeType.PRIMARY -> BadgeAdmission
                    QuickActionBadgeType.WARNING -> Tertiary
                    QuickActionBadgeType.INFO -> Secondary
                    QuickActionBadgeType.SUCCESS -> Primary
                }

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(iconBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = iconVector,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.NorthEast,
                        contentDescription = null,
                        tint = TextTertiary,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = action.title,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = action.description,
                color = TextSecondary,
                fontSize = 11.sp,
                lineHeight = 15.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Badge
            val (badgeBg, badgeTextClr) = when (action.badgeType) {
                QuickActionBadgeType.PRIMARY -> Pair(Color(0xFFE8F5E9), Primary)
                QuickActionBadgeType.WARNING -> Pair(BadgeWarningBg, BadgeWarningText)
                QuickActionBadgeType.INFO -> Pair(SecondaryContainer, Secondary)
                QuickActionBadgeType.SUCCESS -> Pair(BadgeSuccessBg, Primary)
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(badgeBg)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = action.badgeText,
                    color = badgeTextClr,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp
                )
            }
        }
    }
}
