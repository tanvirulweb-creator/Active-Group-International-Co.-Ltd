package com.activegroupintl.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.data.model.Destination
import com.activegroupintl.app.data.model.QuickAction
import com.activegroupintl.app.data.repository.MockDataRepository
import com.activegroupintl.app.ui.components.DestinationReel
import com.activegroupintl.app.ui.components.PromoBanner
import com.activegroupintl.app.ui.components.QuickActionsGrid
import com.activegroupintl.app.ui.components.StatsRow
import com.activegroupintl.app.ui.components.SuccessStoriesList
import com.activegroupintl.app.ui.theme.BadgeSuccessBg
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.Secondary
import com.activegroupintl.app.ui.theme.SurfaceCanvas
import com.activegroupintl.app.ui.theme.SurfaceContainer
import com.activegroupintl.app.ui.theme.SurfaceContainerLow
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    onNavigateToCourses: () -> Unit,
    onNavigateToBookSlot: () -> Unit,
    onNavigateToDestinations: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onQuickActionClick: (QuickAction) -> Unit = {},
    onDestinationClick: (Destination) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // 1. Promo Alert Banner (Claim 1 Free Class)
            PromoBanner(
                onBookFreeClassClick = onNavigateToBookSlot
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Trust Stats Counter Row (27+ Years, 5k+ Placed, etc.)
            StatsRow(stats = MockDataRepository.stats)

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Quick Actions Grid Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Explore Core Services",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Instant Processing",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Actions Grid (Book Counselling, 48-Hr Assessment, IELTS & PTE, Japanese)
            QuickActionsGrid(
                actions = MockDataRepository.quickActions,
                onActionClick = { action ->
                    when (action.id) {
                        "counselling" -> onNavigateToBookSlot()
                        "assessment" -> onNavigateToProfile()
                        "ielts", "japanese" -> onNavigateToCourses()
                        else -> onQuickActionClick(action)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 4. Popular Study Destinations Horizontal Reel
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Study Destinations",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "Curated visa routes & institutional support",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                TextButton(onClick = onNavigateToDestinations) {
                    Text(
                        text = "View All",
                        color = Primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.padding(start = 16.dp)) {
                DestinationReel(
                    destinations = MockDataRepository.destinations,
                    onDestinationClick = { onNavigateToDestinations() }
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 5. Recent Student Visa Success Stories
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Recent Visa Successes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "Real students, confirmed enrolments",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(BadgeSuccessBg)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "100% Genuine",
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            SuccessStoriesList(stories = MockDataRepository.successStories)

            Spacer(modifier = Modifier.height(20.dp))

            // 6. Authorized Global Partner Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AGI",
                            color = Primary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "AUTHORIZED GLOBAL PARTNER",
                            color = Primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Direct institutional tie-ups across Australia, Japan, UK, Canada & USA with end-to-end documentation.",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }
    }
}
