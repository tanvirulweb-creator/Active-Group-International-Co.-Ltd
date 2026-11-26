# Active Group International (AGI Global) 🎓🌏
### Android App (Kotlin + Jetpack Compose)

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Language](https://img.shields.io/badge/Language-Kotlin%202.0-purple.svg)](https://kotlinlang.org)
[![UI](https://img.shields.io/badge/UI-Jetpack%20Compose%20%2F%20Material%203-blue.svg)](https://developer.android.com/jetpack/compose)
[![Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

Official native Android application for **Active Group International Co. Ltd (AGI Global)** — an education consultancy and language learning gateway helping students with language test prep (IELTS, PTE, Japanese JLPT/NAT-TEST) and study abroad visa pathways across Australia, Japan, the UK, Canada, and the USA.

---

## 📱 Features

1. **Modern Material 3 Design System:**
   - Tailored institutional color palette (Foliage Green `#1D6A00`, Navy `#396285`, Warm Gold `#825100`).
   - Clean card-based visual hierarchy, soft ambient elevations, and responsive touch controls.

2. **Core App Screens & Capabilities:**
   - **Home Screen:**
     - Top App Bar with location switcher (*Dhaka Office / Online*), notification bell, and profile badge.
     - **Promo Alert Banner:** *"Claim 1 Free Class!"* with quick booking for Online Zoom or Nayapaltan Center.
     - **Trust Counter Metrics:** 27+ Years Experience, 5k+ Placed, 95% Visa Success Rate, 150+ Partners.
     - **Quick Services Grid:** Instant access to *Book Counselling*, *48-Hr Assessment*, *IELTS/PTE Prep*, and *Japanese Language Program*.
     - **Study Destinations Reel:** Interactive horizontal cards for Australia 🇦🇺, Japan 🇯🇵, UK 🇬🇧, Canada 🇨🇦, and USA 🇺🇸.
     - **Student Success Wall:** Verified visa grant showcases with university and intake details.
     - **Floating WhatsApp Support:** Direct 1-tap connection to official WhatsApp hotline.
   - **Courses Screen:**
     - Filter by *All*, *Language (Japanese JLPT/NAT)*, and *Test Prep (IELTS & PTE)*.
     - Class schedule, duration, curriculum highlights, and direct trial class reservation.
   - **Book Slot Screen:**
     - Interactive reservation for 1-on-1 counseling, online Zoom sessions, or Nayapaltan in-person consultations with preferred time slots.
   - **Destinations Screen:**
     - Detailed study abroad guides, work visa rights (PSW/PGWP/OPT), and university shortlist checks.
   - **Profile & Assessment:**
     - 48-Hour Free Eligibility Audit submission form, official Dhaka and Japan branch office contacts.

---

## 🏗️ Architecture & Tech Stack

- **Architecture:** Clean Architecture with MVVM pattern
- **Language:** Kotlin 2.0
- **UI Toolkit:** Jetpack Compose + Material Design 3
- **Navigation:** Jetpack Navigation Compose (`androidx.navigation.compose`)
- **Icons:** Extended Material Icons (`androidx.compose.material:material-icons-extended`)
- **Build System:** Gradle (Kotlin DSL `.kts`) + Version Catalog (`libs.versions.toml`)

---

## 📂 Project Structure

```
agi-language-class-app/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/activegroupintl/app/
│   │   │   ├── MainActivity.kt
│   │   │   ├── data/
│   │   │   │   ├── model/Models.kt
│   │   │   │   └── repository/MockDataRepository.kt
│   │   │   └── ui/
│   │   │       ├── components/
│   │   │       │   ├── TopAppBarComponent.kt
│   │   │       │   ├── PromoBanner.kt
│   │   │       │   ├── StatsRow.kt
│   │   │       │   ├── QuickActionCards.kt
│   │   │       │   ├── DestinationReel.kt
│   │   │       │   ├── SuccessStoriesList.kt
│   │   │       │   ├── WhatsAppFab.kt
│   │   │       │   └── BottomNavigationBar.kt
│   │   │       ├── navigation/
│   │   │       │   └── Screen.kt
│   │   │       ├── screens/
│   │   │       │   ├── HomeScreen.kt
│   │   │       │   ├── CoursesScreen.kt
│   │   │       │   ├── BookSlotScreen.kt
│   │   │       │   ├── DestinationsScreen.kt
│   │   │       │   └── ProfileScreen.kt
│   │   │       └── theme/
│   │   │           ├── Color.kt
│   │   │           ├── Type.kt
│   │   │           └── Theme.kt
│   │   └── res/
│   │       ├── drawable/
│   │       └── values/
│   └── build.gradle.kts
├── docs/
│   ├── DESIGN.md
│   └── screen.png
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/gradle-wrapper.properties
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🚀 How to Run in Android Studio

1. **Clone the repository:**
   ```bash
   git clone git@github.com:tanvirulweb-creator/Active-Group-International-Co.-Ltd.git
   ```
2. **Open in Android Studio:**
   - Launch Android Studio (Hedgehog / Iguana / Jellyfish / Koala or later).
   - Select **Open** and choose the cloned `Active-Group-International-Co.-Ltd` directory.
3. **Sync & Run:**
   - Allow Gradle to sync dependencies.
   - Select an emulator or connected physical Android device (API 24+).
   - Press **Run ▶**.

---

## 🏢 Organization Contact

**Active Group International Co. Ltd (AGI Global)**
- **Dhaka Campus:** Nayapaltan, Dhaka-1000, Bangladesh
- **Japan Regional Office:** Gunma & Tokyo, Japan
- **WhatsApp Support:** [+880 1335-880011](https://wa.me/8801335880011)
- **Email:** info@activegroupintl.com
