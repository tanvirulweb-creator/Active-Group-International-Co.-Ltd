package com.activegroupintl.app.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WorkspacePremium
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.ui.theme.BadgeAdmission
import com.activegroupintl.app.ui.theme.BadgeAdmissionBg
import com.activegroupintl.app.ui.theme.BadgeSuccessBg
import com.activegroupintl.app.ui.theme.BadgeWarningBg
import com.activegroupintl.app.ui.theme.BadgeWarningText
import com.activegroupintl.app.ui.theme.BorderActive
import com.activegroupintl.app.ui.theme.BorderSubtle
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.PrimaryContainer
import com.activegroupintl.app.ui.theme.Secondary
import com.activegroupintl.app.ui.theme.SurfaceCanvas
import com.activegroupintl.app.ui.theme.SurfaceCard
import com.activegroupintl.app.ui.theme.SurfaceContainer
import com.activegroupintl.app.ui.theme.SurfaceContainerHigh
import com.activegroupintl.app.ui.theme.SurfaceContainerLow
import com.activegroupintl.app.ui.theme.Tertiary
import com.activegroupintl.app.ui.theme.TextPrimary
import com.activegroupintl.app.ui.theme.TextSecondary
import com.activegroupintl.app.ui.theme.TextTertiary

data class TimelineMilestone(
    val year: String,
    val badge: String,
    val badgeColor: Color,
    val badgeTextColor: Color,
    val title: String,
    val description: String,
    val icon: ImageVector
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    onNavigateToBookSlot: () -> Unit = {}
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var showAssessmentDialog by remember { mutableStateOf(false) }
    var studentName by remember { mutableStateOf("") }
    var studentPhone by remember { mutableStateOf("") }
    var studentDegree by remember { mutableStateOf("") }
    var studentCountry by remember { mutableStateOf("") }

    val milestones = listOf(
        TimelineMilestone("1998", "Founding", SurfaceContainerLow, Secondary, "Humble Beginnings", "Active Group founded at a single desk in Nayapaltan, Dhaka with an unwavering vision for educational access.", Icons.Default.Flag),
        TimelineMilestone("1999", "Japan Pathway", BadgeWarningBg, Tertiary, "First International Cohort", "Pioneered direct Japanese language school placements and Certificate of Eligibility (COE) student documentation.", Icons.Default.Translate),
        TimelineMilestone("2005", "Australia", BadgeAdmissionBg, Secondary, "Australian University Alliances", "Initiated institutional agreements with leading Australian universities, polytechnics, and pathway colleges.", Icons.Default.Handshake),
        TimelineMilestone("2012 – 2016", "Global Scale", SurfaceContainerLow, TextSecondary, "Dhaka & Sydney Expansion", "Opened Bashundhara flagship branch opposite NSU, followed by establishing the Sydney associate office for on-shore student settlement.", Icons.Default.Apartment),
        TimelineMilestone("2019", "Japan Branch", SurfaceContainerLow, TextSecondary, "Uttara & Gunma Associate Offices", "Broadened footprint with Uttara hub in Dhaka and direct support liaison based in Gunma / Tokyo, Japan.", Icons.Default.Explore),
        TimelineMilestone("2026 Milestone", "5,000+ Alumni", BadgeSuccessBg, Primary, "Global Education Benchmark", "5,000+ students successfully pursuing higher education across Australia, Japan, Canada, UK, USA, and New Zealand.", Icons.Default.MilitaryTech)
    )

    fun openDialer(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        try { context.startActivity(intent) } catch (_: Exception) {}
    }

    fun openWhatsApp() {
        val uri = Uri.parse("https://wa.me/8801335880011")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try { context.startActivity(intent) } catch (_: Exception) {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 70.dp)
    ) {
        // ==================== 1. HERO & LEGACY SECTION ====================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainerLow)
                .padding(16.dp)
        ) {
            Column {
                // Badge
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Primary))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ACTIVE GROUP INTERNATIONAL",
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        letterSpacing = 0.5.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "A Quarter Century of Turning Ambition into Opportunity",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = TextPrimary,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Operating since 1999 across Bangladesh, Australia, and Japan. Over 27+ years empowering students to study, grow, and succeed worldwide.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Trust Stats Matrix (2x2 Bento grid)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    LegacyStatBox(
                        icon = Icons.Default.HistoryEdu,
                        tint = Secondary,
                        value = "27+ Years",
                        sub = "Established in 1999",
                        modifier = Modifier.weight(1f)
                    )
                    LegacyStatBox(
                        icon = Icons.Default.School,
                        tint = Primary,
                        value = "5,000+",
                        sub = "Global Placements",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    LegacyStatBox(
                        icon = Icons.Default.CorporateFare,
                        tint = Secondary,
                        value = "150+",
                        sub = "Partner Universities",
                        modifier = Modifier.weight(1f)
                    )
                    LegacyStatBox(
                        icon = Icons.Default.Public,
                        tint = Tertiary,
                        value = "3 Nations",
                        sub = "Dhaka • Sydney • Tokyo",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== 2. MULTINATIONAL FOUNDATION & WHO WE ARE ====================
        Text(
            text = "MULTINATIONAL FOUNDATION",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Primary,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "About Active Group International",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Active Group International Co. Ltd. (AGI) is a distinguished multinational business group founded in 1999, operating across Australia, Bangladesh, and Japan.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = "Diverse Group Portfolio",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            PortfolioTag(Icons.Default.School, Primary, "Global Education")
                            PortfolioTag(Icons.Default.FlightTakeoff, Secondary, "Immigration")
                            PortfolioTag(Icons.Default.CellTower, Primary, "Telecom")
                            PortfolioTag(Icons.Default.MedicalServices, Secondary, "Healthcare")
                            PortfolioTag(Icons.Default.Spa, Primary, "Agriculture")
                            PortfolioTag(Icons.Default.Hotel, Secondary, "Hospitality")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Pillars: Mission, Vision, Core Values
        PillarCard(
            icon = Icons.Default.RocketLaunch,
            tint = Secondary,
            bg = SurfaceContainerLow,
            title = "Our Mission",
            desc = "Provide efficient, transparent, and ethical services to students—helping them make the best international education choices based on true aptitude and ambition."
        )
        Spacer(modifier = Modifier.height(8.dp))
        PillarCard(
            icon = Icons.Default.Visibility,
            tint = Primary,
            bg = BadgeSuccessBg,
            title = "Our Vision",
            desc = "Ensure every aspiring student has the opportunity to study abroad and realize their academic potential with a clear, guided roadmap."
        )
        Spacer(modifier = Modifier.height(8.dp))
        PillarCard(
            icon = Icons.Default.VerifiedUser,
            tint = Tertiary,
            bg = BadgeWarningBg,
            title = "Core Values",
            desc = "Absolute sincerity, complete transparency, zero compromise on counselling quality, and proactive empathy toward student anxieties."
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== 3. LEADERSHIP REFLECTIONS ====================
        Text(
            text = "EXECUTIVE GUIDANCE",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Secondary,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "Leadership Reflections",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Chairman Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerLow)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "CHAIRMAN MESSAGE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Secondary,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Md. Abu Zafar Prince",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Active Group International Co. Ltd.",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "“Since our establishment, our vision has remained clear—to provide trusted guidance, professional expertise, and exceptional service to students and families. For over 27 years, we deliver consultancy with integrity, transparency, and genuine care.”",
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(BadgeAdmissionBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = "Dhaka • Sydney • Japan", color = Secondary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(BadgeSuccessBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = "27+ Yrs Proven Integrity", color = Primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Managing Director Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BadgeSuccessBg.copy(alpha = 0.35f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "MANAGING DIRECTOR",
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = Primary,
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = Primary, modifier = Modifier.size(13.dp))
                        }
                        Text(
                            text = "Mr. Towha Mohammad",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Certified Education Counsellor",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "“Studying abroad is more than earning a qualification—it's about building knowledge, confidence, and global perspective to shape a successful future.”",
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerLow)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Badge, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Certified counselor with extensive direct operational experience across Australia, Japan, and Bangladesh multinational institutions.",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== 4. TIMELINE MILESTONES ====================
        Text(
            text = "OUR EVOLUTION",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Primary,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "From One Desk to Three Nations",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextPrimary
        )
        Text(
            text = "Key milestones that defined our 27-year heritage.",
            fontSize = 12.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        milestones.forEachIndexed { index, milestone ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = milestone.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = milestone.year,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = Primary
                            )
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(milestone.badgeColor)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = milestone.badge,
                                    color = milestone.badgeTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }

                        Text(
                            text = milestone.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextPrimary
                        )

                        Text(
                            text = milestone.description,
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== 5. PROFESSIONAL ACCREDITATIONS ====================
        Text(
            text = "QUALITY ASSURANCE",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Secondary,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "Professional Memberships",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(BadgeAdmissionBg), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = Secondary, modifier = Modifier.size(22.dp))
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "ISANA", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
                    Text(text = "International Education Association", fontSize = 10.sp, color = TextSecondary, textAlign = TextAlign.Center)
                }
            }

            Card(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.WorkspacePremium, contentDescription = null, tint = Secondary, modifier = Modifier.size(22.dp))
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "AIIE", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
                    Text(text = "Australian Institute of Int. Education", fontSize = 10.sp, color = TextSecondary, textAlign = TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(BadgeSuccessBg), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.Policy, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = "QEAC & Certified Counsellors", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextPrimary)
                    Text(text = "Certified by British Council & Qualified Education Agent standards for student visa compliance.", fontSize = 11.sp, color = TextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ==================== 6. GLOBAL PRESENCE & OFFICES ====================
        Text(
            text = "REACH OUT",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Primary,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "Global Presence",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Dhaka Head Office
        OfficeCard(
            badge = "Headquarters",
            badgeBg = BadgeSuccessBg,
            badgeColor = Primary,
            title = "Dhaka Head Office",
            address = "12th Floor, Suite #6, City Heart, 67 Nayapaltan, Dhaka-1000, Bangladesh",
            phone = "+880 1335880011",
            altPhone = "+880 1335880033",
            onCall = { openDialer("+8801335880011") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bashundhara Branch
        OfficeCard(
            badge = "Near NSU Gate 2",
            badgeBg = SurfaceContainerLow,
            badgeColor = Secondary,
            title = "Bashundhara Branch",
            address = "House 304/7, Sha Alam Road, Beside North South University Gate 02, Bashundhara R/A, Dhaka",
            email = "activebdhara@gmail.com"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // International locations grid
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)), colors = CardDefaults.cardColors(containerColor = SurfaceCard)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Secondary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Sydney, Australia", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Secondary)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Associate liaison for university transfers, onshore visa extensions & settlement.", fontSize = 10.sp, color = TextSecondary)
                }
            }

            Card(modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)), colors = CardDefaults.cardColors(containerColor = SurfaceCard)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Secondary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Gunma / Tokyo, Japan", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Secondary)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Direct Japanese language school partner network and COE assistance liaison.", fontSize = 10.sp, color = TextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Direct Action Buttons
        Button(
            onClick = onNavigateToBookSlot,
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Book 1-on-1 Free Consultation", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { openWhatsApp() },
            colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard, contentColor = Secondary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().height(48.dp).border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
        ) {
            Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Chat with Counsellor on WhatsApp", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Free Assessment Pill Action
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(BadgeWarningBg)
                .clickable { showAssessmentDialog = true }
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.FactCheck, contentDescription = null, tint = Tertiary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Submit Profile for 48-Hour Free Eligibility Audit", color = BadgeWarningText, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Active Education & Immigration • Active Group International © 2026",
            fontSize = 11.sp,
            color = TextTertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

    // Free Profile Assessment Modal
    if (showAssessmentDialog) {
        AlertDialog(
            onDismissRequest = { showAssessmentDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        if (studentName.isBlank() || studentPhone.isBlank()) {
                            Toast.makeText(context, "Please enter your name and phone number", Toast.LENGTH_SHORT).show()
                        } else {
                            showAssessmentDialog = false
                            Toast.makeText(context, "Audit submitted! We will evaluate your profile and contact you within 48 hours.", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit Free Audit Request", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showAssessmentDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = TextPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Text(text = "48-Hour Free Eligibility Audit", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
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
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = studentPhone,
                        onValueChange = { studentPhone = it },
                        label = { Text("WhatsApp / Mobile Number") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = studentDegree,
                        onValueChange = { studentDegree = it },
                        label = { Text("Highest Degree & CGPA") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = studentCountry,
                        onValueChange = { studentCountry = it },
                        label = { Text("Target Country (e.g. Australia, Japan)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            shape = RoundedCornerShape(14.dp),
            containerColor = SurfaceCard
        )
    }
}

@Composable
fun LegacyStatBox(
    icon: ImageVector,
    tint: Color,
    value: String,
    sub: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainerLow),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp, color = TextPrimary)
            Text(text = sub, fontSize = 10.sp, color = TextTertiary)
        }
    }
}

@Composable
fun PortfolioTag(icon: ImageVector, tint: Color, text: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, BorderSubtle, CircleShape)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(13.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun PillarCard(icon: ImageVector, tint: Color, bg: Color, title: String, desc: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(bg), contentAlignment = Alignment.Center) {
                Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                Text(text = desc, fontSize = 11.sp, color = TextSecondary, lineHeight = 15.sp)
            }
        }
    }
}

@Composable
fun OfficeCard(
    badge: String,
    badgeBg: Color,
    badgeColor: Color,
    title: String,
    address: String,
    phone: String? = null,
    altPhone: String? = null,
    email: String? = null,
    onCall: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(badgeBg)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = badge, color = badgeColor, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = address, fontSize = 11.sp, color = TextSecondary)

            if (phone != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Secondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = phone,
                        color = Secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        modifier = Modifier.clickable { onCall?.invoke() }
                    )
                    if (altPhone != null) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "•", color = TextTertiary, fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = altPhone, color = Secondary, fontWeight = FontWeight.SemiBold, fontSize = 11.sp)
                    }
                }
            }

            if (email != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Mail, contentDescription = null, tint = Secondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = email, color = Secondary, fontWeight = FontWeight.SemiBold, fontSize = 11.sp)
                }
            }
        }
    }
}
