package com.example.exercicescompose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

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
        item { SectionTitle(title = "Compétences") }
        item { SkillsRow(skills = profile.skills) }
        item { SectionTitle(title = "Projets") }
        items(profile.projects) { project ->
            ProjectCard(project = project)
        }
        item { ContactSection(email = profile.email) }
        item {
            ProfileActionButton(
                onClick = { /* Plus tard : envoyer un message */ }
            )
        }
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

// Représente un titre de section ("Compétences", "Projets" etc...)
@Composable
fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

// Représente la ligne de compétences qui défile
@Composable
fun SkillsRow(skills: List<String>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(skills) { skill ->
            SkillCard(skill = skill)
        }
    }
}

// Représente un projet (élément réutilisable de la liste)
@Composable
fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = project.title, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Représente la zone contact (email en lecture seule)
@Composable
fun ContactSection(email: String, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Contact", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Représente le bouton d'action (le parent décide quoi faire au clic)
@Composable
fun ProfileActionButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.Email, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Me contacter", style = MaterialTheme.typography.labelLarge)
    }
}

// Représente UNE compétence (élément réutilisable de la liste)
@Composable
fun SkillCard(skill: String, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = modifier
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp) // padding interne
        )
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
@Preview(showBackground = true, name = "Léa - clair", heightDp = 1000)
@Composable
fun ProfileLightPreview() {
    ExercicesComposeTheme(darkTheme = false) {
        Surface { ProfileScreen(profile = fakeProfiles[0]) }
    }
}

// Preview en mode sombre
@Preview(showBackground = true, name = "Tom - sombre", uiMode = Configuration.UI_MODE_NIGHT_YES, heightDp = 1000)
@Composable
fun ProfileDarkPreview() {
    ExercicesComposeTheme(darkTheme = true) {
        Surface { ProfileScreen(profile = fakeProfiles[1]) }
    }
}