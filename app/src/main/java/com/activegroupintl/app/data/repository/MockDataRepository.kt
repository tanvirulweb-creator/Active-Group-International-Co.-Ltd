package com.activegroupintl.app.data.repository

import com.activegroupintl.app.data.model.Course
import com.activegroupintl.app.data.model.Destination
import com.activegroupintl.app.data.model.QuickAction
import com.activegroupintl.app.data.model.QuickActionBadgeType
import com.activegroupintl.app.data.model.StatItem
import com.activegroupintl.app.data.model.SuccessStory

object MockDataRepository {

    val stats = listOf(
        StatItem("27+", "Years Exp."),
        StatItem("5k+", "Placed"),
        StatItem("95%", "Visa Rate"),
        StatItem("150+", "Partners")
    )

    val quickActions = listOf(
        QuickAction(
            id = "counselling",
            title = "Book Counselling",
            description = "1-on-1 Online or In-person at Dhaka office",
            badgeText = "Slots Available Today",
            badgeType = QuickActionBadgeType.PRIMARY,
            iconName = "calendar"
        ),
        QuickAction(
            id = "assessment",
            title = "48-Hr Assessment",
            description = "Profile eligibility check with university shortlist",
            badgeText = "100% Free Audit",
            badgeType = QuickActionBadgeType.WARNING,
            iconName = "fact_check"
        ),
        QuickAction(
            id = "ielts",
            title = "IELTS & PTE Prep",
            description = "Intensive modules with certified British Council trainer",
            badgeText = "Band 7.5+ Target",
            badgeType = QuickActionBadgeType.INFO,
            iconName = "record_voice_over"
        ),
        QuickAction(
            id = "japanese",
            title = "Japanese Program",
            description = "N5 to N2 preparation & direct Japan work visa pathway",
            badgeText = "NAT / JLPT",
            badgeType = QuickActionBadgeType.SUCCESS,
            iconName = "translate"
        )
    )

    val destinations = listOf(
        Destination(
            id = "au",
            country = "Australia",
            flagEmoji = "🇦🇺",
            badge = "Fast Track",
            highlight1 = "2-4 yr post-study work visa",
            highlight2 = "Go8 & Regional Uni options",
            description = "High visa acceptance with world-leading universities across Sydney, Melbourne, Brisbane and Adelaide.",
            popularCourses = listOf("Master of Information Technology", "Bachelor of Engineering", "MBA", "Nursing & Healthcare")
        ),
        Destination(
            id = "jp",
            country = "Japan",
            flagEmoji = "🇯🇵",
            badge = "Active Office",
            highlight1 = "Gunma & Tokyo local care",
            highlight2 = "Part-time work permitted",
            description = "Exclusive direct partnership with language academies and universities in Gunma & Tokyo with housing support.",
            popularCourses = listOf("Japanese Language School (1-2 yrs)", "Specified Skilled Worker (SSW)", "Automotive Tech", "Hospitality")
        ),
        Destination(
            id = "uk",
            country = "United Kingdom",
            flagEmoji = "🇬🇧",
            badge = "Popular",
            highlight1 = "1-Year Master's Degrees",
            highlight2 = "2-Year Graduate Route PSW",
            description = "Affordable one-year Master's programs with global recognition and simplified visa assessment.",
            popularCourses = listOf("MSc Data Science", "International Business", "Project Management", "Cyber Security")
        ),
        Destination(
            id = "ca",
            country = "Canada",
            flagEmoji = "🇨🇦",
            badge = "PR Pathway",
            highlight1 = "Up to 3-Year PGWP",
            highlight2 = "Spousal Open Work Permit",
            description = "Clear pathways towards permanent residency with renowned public colleges and universities.",
            popularCourses = listOf("Post-Graduate Diplomas", "Computer Science", "Logistics & Supply Chain", "Biotechnology")
        ),
        Destination(
            id = "us",
            country = "United States",
            flagEmoji = "🇺🇸",
            badge = "STEM OPT",
            highlight1 = "3-Year STEM Extension",
            highlight2 = "Merit-based Scholarships",
            description = "Unmatched research opportunities and up to 3 years post-graduation OPT employment authorization.",
            popularCourses = listOf("MS Artificial Intelligence", "Electrical Engineering", "Financial Analytics", "Biomedical Sciences")
        )
    )

    val successStories = listOf(
        SuccessStory(
            id = "1",
            studentName = "Md Moshiour Rahman",
            university = "Swinburne University of Technology",
            degree = "Master of IT",
            intake = "Intake: Feb 2026",
            countryFlag = "🇦🇺",
            status = "Visa Granted"
        ),
        SuccessStory(
            id = "2",
            studentName = "Fahim Shahriar",
            university = "University of Adelaide (Group of Eight)",
            degree = "Bachelor of Eng.",
            intake = "Full Process Done",
            countryFlag = "🇦🇺",
            status = "Visa Granted"
        ),
        SuccessStory(
            id = "3",
            studentName = "Sharmin Akter",
            university = "Tokyo Japanese Academy",
            degree = "JLPT N2 + SSW Visa",
            intake = "Intake: April 2026",
            countryFlag = "🇯🇵",
            status = "COE Approved"
        )
    )

    val courses = listOf(
        Course(
            id = "ielts-intensive",
            title = "IELTS Intensive Masterclass",
            category = "Test Prep",
            target = "Band 7.5+",
            duration = "2.5 Months (36 Classes)",
            mode = "Online Zoom / Nayapaltan Center",
            fee = "BDT 12,500",
            features = listOf(
                "British Council Certified Lead Trainer",
                "15 Full-length Mock Tests with Individual Feedback",
                "1-on-1 Speaking Evaluation Sessions",
                "Free Cambridge Practice Books (15-19)"
            ),
            nextBatchDate = "Starting 5th Next Month"
        ),
        Course(
            id = "japanese-n5",
            title = "Japanese Language - JLPT/NAT N5",
            category = "Language",
            target = "Beginner to N5 Certified",
            duration = "3 Months (48 Classes)",
            mode = "In-Person & Live Interactive Zoom",
            fee = "BDT 15,000",
            features = listOf(
                "Hiragana, Katakana, and 120 Kanji Characters",
                "Native Japanese Accent & Conversation Drills",
                "NAT-TEST & JLPT Past Papers Solution",
                "Direct Visa Guidance for Japan Language Schools"
            ),
            nextBatchDate = "Batch 42 Starting Soon"
        ),
        Course(
            id = "pte-academic",
            title = "PTE Academic Fast-Track",
            category = "Test Prep",
            target = "PTE 65+ (IELTS 7.0 Equivalent)",
            duration = "1.5 Months (24 Classes)",
            mode = "Online Zoom with AI Software Access",
            fee = "BDT 10,000",
            features = listOf(
                "Official Scoring Algorithm Strategy",
                "Daily Speaking & Dictation AI Portal Practice",
                "Template-free Essay Writing Mastery",
                "Weekly Simulated Pearson Mock Tests"
            ),
            nextBatchDate = "Rolling Enrolment Every Monday"
        ),
        Course(
            id = "japanese-n4",
            title = "Japanese Language - JLPT/NAT N4",
            category = "Language",
            target = "Intermediate Communication",
            duration = "3.5 Months (52 Classes)",
            mode = "Nayapaltan Campus / Online Hybrid",
            fee = "BDT 18,000",
            features = listOf(
                "300+ Kanji & Advanced Grammar Structures",
                "Interview Preparation for Japanese Employers",
                "Preparation for SSW (Specified Skilled Worker) Visa",
                "Official Certificate of Completion"
            ),
            nextBatchDate = "Weekend & Evening Slots"
        )
    )
}
