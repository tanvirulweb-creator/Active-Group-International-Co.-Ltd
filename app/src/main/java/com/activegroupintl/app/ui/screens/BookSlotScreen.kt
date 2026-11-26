package com.activegroupintl.app.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.activegroupintl.app.ui.theme.SurfaceContainerHigh
import com.activegroupintl.app.ui.theme.SurfaceContainerLow
import com.activegroupintl.app.ui.theme.Tertiary
import com.activegroupintl.app.ui.theme.TertiaryContainer
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary
import com.activegroupintl.app.ui.theme.TextTertiary

// Data classes for the 4-step wizard
data class ServiceOption(
    val id: String,
    val title: String,
    val description: String,
    val badge: String? = null,
    val icon: ImageVector
)

data class ModeOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val location: String,
    val badge: String,
    val icon: ImageVector
)

data class SubjectItem(
    val name: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

data class CalendarDay(
    val dayOfWeek: String,
    val dateNumber: String,
    val status: String,
    val dateString: String
)

@Composable
fun BookSlotScreen(
    preselectedCourse: String? = null,
    onBookingSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // 1. Service Selection State
    val services = listOf(
        ServiceOption(
            id = "trial_class",
            title = "1 Free Trial Class",
            description = "IELTS Masterclass or Japanese JLPT N5/N4 foundation session.",
            badge = "Top Choice",
            icon = Icons.Default.LocalLibrary
        ),
        ServiceOption(
            id = "career_counselling",
            title = "Career & Study Abroad Counselling",
            description = "Course selection, scholarship matching & global intake mapping.",
            icon = Icons.Default.FlightTakeoff
        ),
        ServiceOption(
            id = "visa_assessment",
            title = "Free Visa & Profile Assessment",
            description = "SOP evaluation, gap justification & embassy checklist audit.",
            icon = Icons.Default.ContactPage
        )
    )
    var selectedService by remember { mutableStateOf(services[0]) }

    // 2. Mode Selection State
    val modes = listOf(
        ModeOption(
            id = "online",
            title = "Online Live 1-on-1",
            subtitle = "HD Video consultation via Google Meet or Zoom from any location.",
            location = "Online Zoom / Meet",
            badge = "Instant Link",
            icon = Icons.Default.Devices
        ),
        ModeOption(
            id = "offline_nayapaltan",
            title = "Offline In-Person",
            subtitle = "Dhaka Head Office: 12th Fl, City Heart, 67 Nayapaltan, Dhaka.",
            location = "Head Office Nayapaltan",
            badge = "Nayapaltan",
            icon = Icons.Default.Apartment
        ),
        ModeOption(
            id = "offline_bashundhara",
            title = "Offline In-Person",
            subtitle = "House No: 304/7 (1st Fl), Sha Alam Rd, Near NSU Gate 02, Bashundhara R/A, Dhaka.",
            location = "Bashundhara Branch",
            badge = "Bashundhara Branch",
            icon = Icons.Default.Apartment
        )
    )
    var selectedMode by remember { mutableStateOf(modes[1]) }

    // 3. Subject / Destination State
    val subjects = listOf(
        SubjectItem("IELTS Prep", "IELTS Comprehensive Academic & General", "Band 7.5+ targeting with Cambridge certified instructors", Icons.Default.MenuBook),
        SubjectItem("Japanese (JLPT)", "Japanese Language & Culture Mastery", "N5 to N2 preparation & direct Japan work visa pathway", Icons.Default.Translate),
        SubjectItem("Australia", "Australia Study & Post-Study Work Visa", "Direct representation with Go8 and top regional universities", Icons.Default.Flight),
        SubjectItem("Japan", "Japan Language Academy & University Enrolment", "Tokyo & Gunma local care with part-time work permit guidance", Icons.Default.LocationCity),
        SubjectItem("UK", "United Kingdom 1-Year Master's Degrees", "2-year Graduate Route PSW and quick CAS issuance", Icons.Default.School),
        SubjectItem("Canada", "Canada Higher Education & PR Pathway", "Post-Graduate Work Permit (PGWP) and public college diplomas", Icons.Default.Nature),
        SubjectItem("USA", "United States Universities & STEM OPT", "Up to 3-year post-study OPT with merit scholarships", Icons.Default.Stars)
    )
    var selectedSubject by remember { mutableStateOf(subjects[0]) }

    // 4. Calendar & Time State
    val calendarDays = listOf(
        CalendarDay("Thu", "24", "Today", "Thu, Oct 24"),
        CalendarDay("Fri", "25", "Available", "Fri, Oct 25"),
        CalendarDay("Sat", "26", "Available", "Sat, Oct 26"),
        CalendarDay("Sun", "27", "Available", "Sun, Oct 27"),
        CalendarDay("Mon", "28", "Available", "Mon, Oct 28"),
        CalendarDay("Tue", "29", "Available", "Tue, Oct 29")
    )
    var selectedDay by remember { mutableStateOf(calendarDays[0]) }

    val morningSlots = listOf("10:30 AM", "11:30 AM")
    val afternoonSlots = listOf("02:30 PM", "04:00 PM")
    val eveningSlots = listOf("06:00 PM", "07:30 PM")
    var selectedTime by remember { mutableStateOf("10:30 AM") }

    // Form inputs & reminders
    var studentName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var whatsappReminder by remember { mutableStateOf(true) }
    var smsReminder by remember { mutableStateOf(true) }

    // Success dialog modal
    var showSuccessModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 70.dp)
    ) {
        // Micro Celebration Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(PrimaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "100% Free Initial Session",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "No hidden charges • Expert guidance",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BadgeSuccessBg)
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "Active",
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Progress Flow Indicator
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Booking Workflow",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Primary
                )
                Text(
                    text = "All 4 Steps Included",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { 1.0f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Primary,
                trackColor = SurfaceContainerHigh
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ==================== STEP 1: SERVICE SELECTION ====================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "1", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Select Service Type",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }
            Text(
                text = "REQUIRED",
                color = Primary,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        services.forEach { service ->
            val isSelected = selectedService.id == service.id
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = if (isSelected) 1.5.dp else 1.dp,
                        color = if (isSelected) Primary else BorderSubtle,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { selectedService = service },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) SurfaceCard else SurfaceContainerLow
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) SurfaceContainerHigh else Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = service.icon,
                            contentDescription = null,
                            tint = if (isSelected) Primary else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = service.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextPrimary
                            )
                            service.badge?.let { badgeText ->
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(BadgeAdmissionBg)
                                        .padding(horizontal = 6.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = badgeText,
                                        color = BadgeAdmission,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                        Text(
                            text = service.description,
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Primary else SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== STEP 2: MODE SELECTION ====================
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Primary),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "2", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Choose Mode",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        modes.forEach { mode ->
            val isSelected = selectedMode.id == mode.id
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = if (isSelected) 1.5.dp else 1.dp,
                        color = if (isSelected) Primary else BorderSubtle,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { selectedMode = mode },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) SurfaceCard else SurfaceContainerLow
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) SecondaryContainer else Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = mode.icon,
                            contentDescription = null,
                            tint = if (isSelected) Secondary else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = mode.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextPrimary
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        if (mode.id == "online") BadgeSuccessBg else BadgeWarningBg
                                    )
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = mode.badge,
                                    color = if (mode.id == "online") Primary else TertiaryContainer,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }
                        Text(
                            text = mode.subtitle,
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Primary else SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== STEP 3: SUBJECT OR DESTINATION ====================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "3", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Subject or Destination",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }
            Text(
                text = "Tap to pick",
                fontSize = 12.sp,
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontal filter chips
        val subjectScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(subjectScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            subjects.forEach { item ->
                val isSelected = selectedSubject.name == item.name
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isSelected) Primary else SurfaceContainerHigh)
                        .clickable { selectedSubject = item }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = if (isSelected) Color.White else TextPrimary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = item.name,
                            color = if (isSelected) Color.White else TextPrimary,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Context card for chosen subject
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = selectedSubject.icon,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = selectedSubject.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = selectedSubject.subtitle,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== STEP 4: CALENDAR & TIME SLOT ====================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "4", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Calendar & Time Slot",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }
            Text(
                text = "GMT+6 Dhaka Time",
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = Secondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontal Calendar Strip
        val calendarScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(calendarScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            calendarDays.forEach { day ->
                val isSelected = selectedDay.dateString == day.dateString
                Card(
                    modifier = Modifier
                        .width(62.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Primary else BorderSubtle,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedDay = day },
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Primary else SurfaceCard
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = day.dayOfWeek,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) Color.White.copy(alpha = 0.9f) else TextSecondary
                        )
                        Text(
                            text = day.dateNumber,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isSelected) Color.White else TextPrimary
                        )
                        Text(
                            text = day.status,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isSelected) Color.White.copy(alpha = 0.85f) else Primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Morning Time Slots
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.WbSunny, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(15.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "MORNING SLOTS", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextSecondary)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            morningSlots.forEach { slot ->
                val isSelected = selectedTime == slot
                TimeSlotButton(slot = slot, isSelected = isSelected, modifier = Modifier.weight(1f)) {
                    selectedTime = slot
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Afternoon Time Slots
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.LightMode, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(15.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "AFTERNOON SLOTS", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextSecondary)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            afternoonSlots.forEach { slot ->
                val isSelected = selectedTime == slot
                TimeSlotButton(slot = slot, isSelected = isSelected, modifier = Modifier.weight(1f)) {
                    selectedTime = slot
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Evening Time Slots
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.NightsStay, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(15.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "EVENING SLOTS", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextSecondary)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            eveningSlots.forEach { slot ->
                val isSelected = selectedTime == slot
                TimeSlotButton(slot = slot, isSelected = isSelected, modifier = Modifier.weight(1f)) {
                    selectedTime = slot
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== ASSIGNED MENTOR & ADVISOR PROFILE ====================
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ASSIGNED MENTOR & ADVISOR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = TextSecondary,
                        letterSpacing = 0.5.sp
                    )
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(BadgeAdmissionBg)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "✓ Certified Advisor",
                            color = BadgeAdmission,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Tanzim Ahmed, M.Ed",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "British Council IELTS Trainer • 8+ Yrs Ex.",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Tertiary, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "4.96 (420+ reviews)", fontSize = 11.sp, color = Tertiary, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "•", color = TextTertiary, fontSize = 11.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "98% Visa Success", fontSize = 11.sp, color = Primary, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Live status pill
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Primary))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Available on Selected Slot", fontSize = 11.sp, color = TextPrimary)
                        }
                        Text(text = "45 Mins Session", fontSize = 11.sp, color = Secondary, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== STUDENT CONTACT FORM ====================
        Text(
            text = "Your Contact Details",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("WhatsApp / Mobile Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ==================== REMINDER PREFERENCES ====================
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.NotificationsActive, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Instant Appointment Reminders", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                    }
                    Text(text = "Free Alerts", color = Primary, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "WhatsApp Meeting Link & Prep Notes", fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = TextPrimary)
                        Text(text = "Sends Zoom link & map location 30m prior", fontSize = 11.sp, color = TextSecondary)
                    }
                    Switch(
                        checked = whatsappReminder,
                        onCheckedChange = { whatsappReminder = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Primary)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "SMS Confirmation & Queue Code", fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = TextPrimary)
                        Text(text = "Direct confirmation to registered mobile", fontSize = 11.sp, color = TextSecondary)
                    }
                    Switch(
                        checked = smsReminder,
                        onCheckedChange = { smsReminder = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Primary)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ==================== SUMMARY RECAP & SUBMISSION CTA ====================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Selected Slot & Mode", fontSize = 11.sp, color = TextSecondary)
                    Text(
                        text = "${selectedMode.location} • ${selectedDay.dateString} @ $selectedTime",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = BadgeAdmission
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Consultation Fee", fontSize = 10.sp, color = TextSecondary)
                    Text(text = "FREE", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Primary)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (studentName.isBlank() || phone.isBlank()) {
                    Toast.makeText(context, "Please enter your name and mobile number", Toast.LENGTH_SHORT).show()
                } else {
                    showSuccessModal = true
                    onBookingSuccess()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Confirm Booking (No Payment Required)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Primary, modifier = Modifier.size(12.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "100% confidential. Reschedule anytime with 1 tap.", fontSize = 11.sp, color = TextSecondary)
        }
    }

    // ==================== SUCCESS MODAL DIALOG ====================
    if (showSuccessModal) {
        AlertDialog(
            onDismissRequest = { showSuccessModal = false },
            confirmButton = {
                Button(
                    onClick = { showSuccessModal = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done / Back to Home", fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(BadgeSuccessBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Celebration,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(BadgeAdmissionBg)
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(text = "Appointment Confirmed!", color = BadgeAdmission, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "You are all set for global success!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Your booking for \"${selectedService.title}\" with Tanzim Ahmed has been scheduled for ${selectedDay.dateString} at $selectedTime (${selectedMode.location}).",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                    ) {
                        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Primary, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "Calendar invite added to your email", fontSize = 11.sp, color = TextPrimary)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Primary, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "WhatsApp link dispatched to $phone", fontSize = 11.sp, color = TextPrimary)
                            }
                        }
                    }
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = SurfaceCard
        )
    }
}

@Composable
fun TimeSlotButton(
    slot: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) Primary else BorderSubtle,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Primary else SurfaceCard
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = slot,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else TextPrimary
            )
            Text(
                text = if (isSelected) "Selected" else "Available",
                fontSize = 10.sp,
                color = if (isSelected) Color.White.copy(alpha = 0.85f) else TextSecondary
            )
        }
    }
}
