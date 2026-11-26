package com.example.exercicescompose

data class Profile(
    val fullName: String,
    val job: String,
    val description: String,
    val skills: List<String>,
    val isAvailable: Boolean,
    val avatarRes: Int,
    val email: String,
    val projects: List<Project>
)

data class Project(
    val title: String,
    val description: String
)