package com.example.exercicescompose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exercicescompose.ui.theme.ExercicesComposeTheme

// Représente l'écran principal : une liste qui défile
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profile: Profile = fakeProfiles[0]
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ProfileHeader(profile = profile) }
    }
}

// Représente l'en-tête (fond coloré + avatar + textes)
@Composable
fun ProfileHeader(profile: Profile, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarWithBadge(
                avatarRes = profile.avatarRes,
                isAvailable = profile.isAvailable
            )
            Spacer(modifier = Modifier.height(12.dp))
            ProfileFullName(fullName = profile.fullName)
            ProfileJob(job = profile.job)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileDescription(description = profile.description)
        }
    }
}

// Représente l'avatar rond avec le badge par-dessus
@Composable
fun AvatarWithBadge(
    avatarRes: Int,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Dessous : la photo
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = "Photo de profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
        )
        // Dessus : le badge, en bas à droite
        Surface(
            color = if (isAvailable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            contentColor = if (isAvailable) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onError,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(28.dp)
        ) {
            Icon(
                imageVector = if (isAvailable) Icons.Default.Check else Icons.Default.Close,
                contentDescription = if (isAvailable) "Disponible" else "Indisponible",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ProfileFullName(fullName: String, modifier: Modifier = Modifier) {
    Text(
        text = fullName,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Composable
fun ProfileJob(job: String, modifier: Modifier = Modifier) {
    Text(
        text = job,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun ProfileDescription(description: String, modifier: Modifier = Modifier) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

// Preview en mode clair
@Preview(showBackground = true, name = "Léa - clair")
@Composable
fun ProfileLightPreview() {
    ExercicesComposeTheme(darkTheme = false) {
        Surface { ProfileScreen(profile = fakeProfiles[0]) }
    }
}

// Preview en mode sombre
@Preview(showBackground = true, name = "Tom - sombre", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileDarkPreview() {
    ExercicesComposeTheme(darkTheme = true) {
        Surface { ProfileScreen(profile = fakeProfiles[1]) }
    }
}