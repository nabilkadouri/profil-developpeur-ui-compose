package com.example.exercicescompose

val fakeProfiles = listOf(
    Profile(
        fullName = "Léa Martin",
        job = "Développeuse Android",
        description = "Passionnée par les applis mobiles, j'aime créer des interfaces simples et agréables.",
        skills = listOf("Kotlin", "Jetpack Compose", "Git", "Material 3", "Android Studio"),
        isAvailable = true,
        avatarRes = R.drawable.avatar_lea,
        email = "lea@profile.com",
        projects = listOf(
            Project(title = "MesProduits", description = "Appli de liste de produits en Compose."),
            Project(title = "Météo Express", description = "Écran météo simple avec icônes et cartes."),
            Project(title = "Ma Recette", description = "Fiche recette avec ingrédients et étapes.")
        )
    ),
    Profile(
        fullName = "Tom Bernard",
        job = "Designer UX/UI",
        description = "Je dessine des écrans clairs et je teste mes idées auprès des utilisateurs.",
        skills = listOf("Figma", "Prototypage", "Accessibilité", "Design System"),
        isAvailable = false,
        avatarRes = R.drawable.avatar_tom,
        email = "tom@profile.com",
        projects = listOf(
            Project(title = "Refonte BankApp", description = "Nouvelle maquette d'une appli bancaire."),
            Project(title = "Kit d'icônes", description = "50 icônes simples pour une appli mobile.")
        )
    )
)