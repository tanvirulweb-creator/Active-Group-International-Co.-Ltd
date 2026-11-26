package com.activegroupintl.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.R
import com.activegroupintl.app.ui.theme.WhatsAppGreen

@Composable
fun WhatsAppFab(
    onWhatsAppClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onWhatsAppClick,
        containerColor = WhatsAppGreen,
        contentColor = Color.White,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.padding(bottom = 60.dp, end = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_whatsapp),
                contentDescription = "WhatsApp",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "WhatsApp",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }
    }
}
