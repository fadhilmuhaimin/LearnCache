package com.example.learncache.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val id: String,
    val username: String,
    val avatar: String
)