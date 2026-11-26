package com.activegroupintl.app.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.R
import com.activegroupintl.app.ui.theme.Primary

@Composable
fun AppLogoImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = "Active Group International Logo"
) {
    val context = LocalContext.current
    val bitmap = remember {
        try {
            BitmapFactory.decodeResource(context.resources, R.drawable.app_logo)?.asImageBitmap()
        } catch (e: Throwable) {
            null
        }
    }

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            modifier = modifier
        )
    } else {
        // Safe fallback badge if bitmap fails to decode on any unusual vendor ROM
        Box(
            modifier = modifier.background(Primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AGI",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp
            )
        }
    }
}
