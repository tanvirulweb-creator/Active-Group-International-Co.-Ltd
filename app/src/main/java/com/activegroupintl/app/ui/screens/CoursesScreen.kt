package com.activegroupintl.app.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.TempleBuddhist
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.ViewQuilt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.data.model.Course
import com.activegroupintl.app.ui.theme.BadgeAdmission
import com.activegroupintl.app.ui.theme.BadgeAdmissionBg
import com.activegroupintl.app.ui.theme.BadgeSuccessBg
import com.activegroupintl.app.ui.theme.BadgeWarningBg
import com.activegroupintl.app.ui.theme.BorderSubtle
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.PrimaryContainer
import com.activegroupintl.app.ui.theme.Secondary
import com.activegroupintl.app.ui.theme.SecondaryContainer
import com.activegroupintl.app.ui.theme.SurfaceCanvas
import com.activegroupintl.app.ui.theme.SurfaceCard
import com.activegroupintl.app.ui.theme.SurfaceContainer
import com.activegroupintl.app.ui.theme.SurfaceContainerHigh
import com.activegroupintl.app.ui.theme.SurfaceContainerLow
import com.activegroupintl.app.ui.theme.Tertiary
import com.activegroupintl.app.ui.theme.TertiaryFixed
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary
import com.activegroupintl.app.ui.theme.TextTertiary

@Composable
fun CoursesScreen(
    onBookCourse: (Course) -> Unit = {}
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Filter state
    var selectedCategory by remember { mutableStateOf("all") }
    // Trial hero pass switcher
    var selectedTrialType by remember { mutableStateOf("ielts") } // "ielts" or "japanese"

    // Booking reservation dialog state
    var showBookingModal by remember { mutableStateOf(false) }
    var modalCourseTitle by remember { mutableStateOf("") }
    var modalIsFreeTrial by remember { mutableStateOf(false) }
    var studentName by remember { mutableStateOf("") }
    var studentPhone by remember { mutableStateOf("") }

    fun openReservation(title: String, isFree: Boolean) {
        modalCourseTitle = title
        modalIsFreeTrial = isFree
        showBookingModal = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // 1. Top Horizontal Filter Chips Bar
        val chipScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(chipScrollState)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterCategoryPill(
                title = "All Courses",
                icon = Icons.Default.Apps,
                isSelected = selectedCategory == "all",
                onClick = { selectedCategory = "all" }
            )
            FilterCategoryPill(
                title = "IELTS & PTE",
                icon = Icons.Default.MenuBook,
                isSelected = selectedCategory == "ielts",
                onClick = { selectedCategory = "ielts" }
            )
            FilterCategoryPill(
                title = "Japanese (JLPT/NAT)",
                icon = Icons.Default.Translate,
                isSelected = selectedCategory == "japanese",
                onClick = { selectedCategory = "japanese" }
            )
            FilterCategoryPill(
                title = "Free Classes",
                icon = Icons.Default.CardGiftcard,
                isSelected = selectedCategory == "free",
                isGift = true,
                onClick = { selectedCategory = "free" }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2. Featured Delight Hero Card: Free Trial Pass
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Header with Zero-Cost Discovery badge & seats counter
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(BadgeAdmissionBg)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "ZERO-COST DISCOVERY",
                                color = Secondary,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 10.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(Primary)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "8 Seats Left Today",
                            color = Tertiary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Interactive Switcher Toggles (IELTS vs Japanese)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainer)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // IELTS Toggle
                    val isIelts = selectedTrialType == "ielts"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isIelts) SurfaceCard else Color.Transparent)
                            .clickable { selectedTrialType = "ielts" }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.RecordVoiceOver,
                                contentDescription = null,
                                tint = if (isIelts) Primary else TextSecondary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Free IELTS Trial",
                                color = if (isIelts) Primary else TextSecondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    // Japanese Toggle
                    val isJapanese = selectedTrialType == "japanese"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isJapanese) SurfaceCard else Color.Transparent)
                            .clickable { selectedTrialType = "japanese" }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.TempleBuddhist,
                                contentDescription = null,
                                tint = if (isJapanese) Primary else TextSecondary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Free Japanese 1-Day",
                                color = if (isJapanese) Primary else TextSecondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dynamic Context Content based on toggle
                if (selectedTrialType == "ielts") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TrialFeatureBox(icon = Icons.Default.Timer, tint = Secondary, topText = "60 Minutes", subText = "Live Interactive", modifier = Modifier.weight(1f))
                        TrialFeatureBox(icon = Icons.Default.AutoStories, tint = Primary, topText = "Free Kit", subText = "Cambridge PDF+Tips", modifier = Modifier.weight(1f))
                        TrialFeatureBox(icon = Icons.Default.Speed, tint = Tertiary, topText = "Band Score", subText = "Diagnostic Test", modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceCard.copy(alpha = 0.85f))
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(SecondaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Conducted by British Council Accredited Master Trainer. Experience live speaking diagnostic session.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TrialFeatureBox(icon = Icons.Default.Schedule, tint = Secondary, topText = "60 Minutes", subText = "Hiragana / Katakana", modifier = Modifier.weight(1f))
                        TrialFeatureBox(icon = Icons.Default.Draw, tint = Primary, topText = "Stroke Guide", subText = "Digital Worksheets", modifier = Modifier.weight(1f))
                        TrialFeatureBox(icon = Icons.Default.FlightTakeoff, tint = Tertiary, topText = "Visa Brief", subText = "Gunma Pathway", modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceCard.copy(alpha = 0.85f))
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(TertiaryFixed),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.MilitaryTech, contentDescription = null, tint = Tertiary, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Taught by JLPT N1 Certified Sensei. Learn basic greetings, pronunciation nuances & visa requirements.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Reserve Free Seat Button
                Button(
                    onClick = {
                        val title = if (selectedTrialType == "ielts") "Free IELTS Trial" else "Free Japanese 1-Day Trial"
                        openReservation(title, isFree = true)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Icon(imageVector = Icons.Default.HowToReg, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (selectedTrialType == "ielts") "Reserve Free Seat for IELTS Trial" else "Reserve Free Seat for Japanese Trial",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Structured Programs Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Structured Programs",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Official test prep & accredited visa language training",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SurfaceContainer)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.School, contentDescription = null, tint = Secondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "3 Programs", color = Secondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4. Course Cards List
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // CARD 1: IELTS Master Preparation
            if (selectedCategory == "all" || selectedCategory == "ielts" || selectedCategory == "free") {
                RichCourseCard(
                    title = "IELTS Master Preparation",
                    categoryBadge = "Online + Offline Dhaka Center",
                    subBadge = "Regular & Crash Batches",
                    nextBatch = "Next Batch: 15 Oct, 2024",
                    price = "BDT 14,500",
                    trainerSubtitle = "British Council Certified Senior Trainers",
                    rating = "4.9",
                    reviewCount = "420+",
                    highlight1Title = "4 Complete Modules",
                    highlight1Sub = "Listening, Reading, Write, Speak",
                    highlight2Title = "10 Full Mock Tests",
                    highlight2Sub = "Official Exam Hall Simulator",
                    week1Description = "Academic Writing Task 1 & 2 framework analysis with real band 8+ model essays.",
                    week2Description = "Speed reading skimming techniques and complex multi-speaker listening audio drills.",
                    scheduleText = "Evening (7 PM - 9 PM) or Weekend (10 AM - 1 PM) slots available.",
                    onTrialClick = { openReservation("IELTS Free Trial", isFree = true) },
                    onEnrollClick = { openReservation("IELTS Master Preparation", isFree = false) }
                )
            }

            // CARD 2: Japanese Language N5 & N4
            if (selectedCategory == "all" || selectedCategory == "japanese" || selectedCategory == "free") {
                RichCourseCard(
                    title = "Japanese Language N5 & N4",
                    categoryBadge = "Study in Japan Direct Visa Pathway",
                    subBadge = "NAT & JLPT",
                    nextBatch = "Dhaka Center + Gunma Liaison",
                    price = "BDT 18,000",
                    trainerSubtitle = "Gunma Active Group Office Direct Pathway",
                    rating = "5.0",
                    reviewCount = "280+",
                    highlight1Title = "Certified Sensei",
                    highlight1Sub = "Native & JLPT N1 Mentors",
                    highlight2Title = "150 Hours Log",
                    highlight2Sub = "Embassy Standard Proof",
                    calloutBadge = "100% COE & Visa Documentation Support",
                    calloutSub = "Direct university / language school placement in Japan",
                    week1Description = "N5 Level (Month 1-3): Master 800 basic vocabulary, 100 Kanji, and elementary sentence structures.",
                    week2Description = "N4 Level (Month 4-6): 1,500 vocabulary words, 300 Kanji, everyday situational conversations & NAT-TEST Q/A patterns.",
                    scheduleText = "Classes: 4 days/week (Morning 9:30 AM & Evening 5:30 PM).",
                    onTrialClick = { openReservation("Japanese Free 1-Day Trial", isFree = true) },
                    onEnrollClick = { openReservation("Japanese Language N5 & N4", isFree = false) }
                )
            }

            // CARD 3: PTE Academic Sprint
            if (selectedCategory == "all" || selectedCategory == "ielts") {
                RichCourseCard(
                    title = "PTE Academic Sprint",
                    categoryBadge = "100% Computer Lab Simulated",
                    subBadge = "4-Week Fast Track",
                    nextBatch = "Includes AI Score Engine",
                    price = "BDT 12,000",
                    trainerSubtitle = "AI-Scored Mocks & Pearson Format Algorithm",
                    rating = "4.8",
                    reviewCount = "165+",
                    highlight1Title = "AI Pearson Scoring",
                    highlight1Sub = "Instant Pronunciation Matrix",
                    highlight2Title = "Dedicated Lab Seats",
                    highlight2Sub = "Dhaka Banani Tech Hub",
                    week1Description = "Week 1-2: Read Aloud, Repeat Sentence acoustic pitch hacks & Summarize Written Text templates.",
                    week2Description = "Week 3-4: Write From Dictation master list memorization + 5 Timed Proctored Machine Mocks.",
                    scheduleText = "Computer lab access: Mon - Thu (11 AM - 1 PM or 4 PM - 6 PM).",
                    onTrialClick = { openReservation("PTE Free Mock Trial", isFree = true) },
                    onEnrollClick = { openReservation("PTE Academic Sprint", isFree = false) }
                )
            }
        }
    }

    // Interactive Reservation Modal Dialog
    if (showBookingModal) {
        AlertDialog(
            onDismissRequest = { showBookingModal = false },
            confirmButton = {
                Button(
                    onClick = {
                        if (studentName.isBlank() || studentPhone.isBlank()) {
                            Toast.makeText(context, "Please enter your name and phone number", Toast.LENGTH_SHORT).show()
                        } else {
                            showBookingModal = false
                            Toast.makeText(context, "Seat reserved for $studentName! Our advisor will contact you on $studentPhone.", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm Seat Reservation", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showBookingModal = false },
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = TextPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(BadgeSuccessBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Primary, modifier = Modifier.size(28.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (modalIsFreeTrial) "Reserve Free Seat" else "Enrol in Course",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = modalCourseTitle,
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Your Full Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = studentPhone,
                        onValueChange = { studentPhone = it },
                        label = { Text("WhatsApp / Phone Number") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerLow)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "✓ Instant confirmation via SMS & WhatsApp. No upfront fee required for booking inquiry.",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = SurfaceCard
        )
    }
}

@Composable
fun FilterCategoryPill(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    isGift: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (isSelected) Secondary else SurfaceContainer
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) Color.White else (if (isGift) Tertiary else TextSecondary),
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = title,
                color = if (isSelected) Color.White else TextPrimary,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun TrialFeatureBox(
    icon: ImageVector,
    tint: Color,
    topText: String,
    subText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 4.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = topText, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextPrimary)
            Text(text = subText, fontSize = 9.sp, color = TextTertiary)
        }
    }
}

@Composable
fun RichCourseCard(
    title: String,
    categoryBadge: String,
    subBadge: String,
    nextBatch: String,
    price: String,
    trainerSubtitle: String,
    rating: String,
    reviewCount: String,
    highlight1Title: String,
    highlight1Sub: String,
    highlight2Title: String,
    highlight2Sub: String,
    calloutBadge: String? = null,
    calloutSub: String? = null,
    week1Description: String,
    week2Description: String,
    scheduleText: String,
    onTrialClick: () -> Unit,
    onEnrollClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "accordion")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            // Visual Top Header Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Primary)
                    .padding(12.dp)
            ) {
                // Badges top row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(BadgeAdmissionBg)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(text = categoryBadge, color = Secondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(BadgeSuccessBg)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(text = subBadge, color = Primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Batch info & Price bottom row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, tint = Color.White.copy(alpha = 0.9f), modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = nextBatch, color = Color.White.copy(alpha = 0.95f), fontSize = 11.sp)
                    }
                    Text(text = price, color = Color(0xFF81DC5F), fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                }
            }

            // Body
            Column(modifier = Modifier.padding(14.dp)) {
                // Title and rating
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)) {
                            Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = Tertiary, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = trainerSubtitle, fontSize = 11.sp, color = TextSecondary)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Tertiary, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = rating, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextPrimary)
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "($reviewCount)", fontSize = 9.sp, color = TextTertiary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Highlights Mosaic Grid
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.ViewQuilt, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(text = highlight1Title, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = TextPrimary)
                            Text(text = highlight1Sub, fontSize = 9.sp, color = TextTertiary)
                        }
                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.AssignmentTurnedIn, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(text = highlight2Title, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = TextPrimary)
                            Text(text = highlight2Sub, fontSize = 9.sp, color = TextTertiary)
                        }
                    }
                }

                // Optional Value Proposition callout
                if (calloutBadge != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(BadgeAdmissionBg.copy(alpha = 0.6f))
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Badge, contentDescription = null, tint = Secondary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(text = calloutBadge, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Secondary)
                                calloutSub?.let { Text(text = it, fontSize = 10.sp, color = TextSecondary) }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Collapsible Syllabus Accordion
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainer)
                        .clickable { isExpanded = !isExpanded }
                        .padding(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Preview Course Modules & Schedule",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Secondary
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier
                                    .size(18.dp)
                                    .rotate(rotationState)
                            )
                        }

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(verticalAlignment = Alignment.Top) {
                                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Primary).padding(top = 4.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = week1Description, fontSize = 11.sp, color = TextSecondary)
                                }
                                Row(verticalAlignment = Alignment.Top) {
                                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Primary).padding(top = 4.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = week2Description, fontSize = 11.sp, color = TextSecondary)
                                }
                                Row(verticalAlignment = Alignment.Top) {
                                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Primary).padding(top = 4.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = scheduleText, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dual CTAs (Free Trial vs Book Seat)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onTrialClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SurfaceContainerHigh,
                            contentColor = Secondary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.PlayCircle, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Free Trial", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Button(
                        onClick = onEnrollClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Book Seat", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
