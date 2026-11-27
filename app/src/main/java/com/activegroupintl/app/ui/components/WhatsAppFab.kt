package com.activegroupintl.app.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.activegroupintl.app.ui.theme.WhatsAppGreen

@Composable
fun WhatsAppFab(
    onWhatsAppClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onWhatsAppClick,
        containerColor = WhatsAppGreen,
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.size(52.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Chat,
            contentDescription = "WhatsApp",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}
