package com.activegroupintl.app.ui.screens

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.data.model.Destination
import com.activegroupintl.app.data.model.QuickAction
import com.activegroupintl.app.data.repository.MockDataRepository
import com.activegroupintl.app.ui.components.AppLogoImage
import com.activegroupintl.app.ui.components.DestinationReel
import com.activegroupintl.app.ui.components.PromoBanner
import com.activegroupintl.app.ui.components.QuickActionsGrid
import com.activegroupintl.app.ui.components.StatsRow
import com.activegroupintl.app.ui.components.SuccessStoriesList
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.SurfaceCanvas
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary
import com.activegroupintl.app.ui.theme.TextTertiary

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
            .padding(bottom = 90.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Hero Promo Banner
            PromoBanner(
                onBookFreeClassClick = onNavigateToBookSlot
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Trust Stats Counter Row
            StatsRow(stats = MockDataRepository.stats)

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Quick Actions Header
            Text(
                text = "Services",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Quick Actions 2x2 Grid
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

        Spacer(modifier = Modifier.height(26.dp))

        // 4. Popular Destinations Horizontal Reel
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Study Destinations",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextPrimary
                )

                TextButton(onClick = onNavigateToDestinations) {
                    Text(
                        text = "View All",
                        color = Primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(13.dp)
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

        Spacer(modifier = Modifier.height(26.dp))

        // 5. Recent Student Visa Success Stories
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Recent Visa Grants",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            SuccessStoriesList(stories = MockDataRepository.successStories)

            Spacer(modifier = Modifier.height(28.dp))

            // 6. Authorized Global Partner Minimal Footer
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppLogoImage(
                        contentDescription = "Active Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Active Group International",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Govt. Approved Education & Visa Consultant • 27+ Years",
                            color = TextTertiary,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}
