package com.activegroupintl.app

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class CrashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val errorDetails = intent.getStringExtra("crash_error") ?: "Unknown error"

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 48)
            setBackgroundColor(android.graphics.Color.WHITE)
        }

        val title = TextView(this).apply {
            text = "Active Group International - Startup Notice"
            textSize = 18f
            setTextColor(android.graphics.Color.RED)
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 16)
        }
        layout.addView(title)

        val copyButton = Button(this).apply {
            text = "Copy Error Details"
            setOnClickListener {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Crash Error", errorDetails)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this@CrashActivity, "Error copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }
        layout.addView(copyButton)

        val scrollView = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply { topMargin = 24 }
        }

        val errorTextView = TextView(this).apply {
            text = errorDetails
            textSize = 12f
            setTextColor(android.graphics.Color.DKGRAY)
            setTextIsSelectable(true)
        }
        scrollView.addView(errorTextView)
        layout.addView(scrollView)

        setContentView(layout)
    }
}
