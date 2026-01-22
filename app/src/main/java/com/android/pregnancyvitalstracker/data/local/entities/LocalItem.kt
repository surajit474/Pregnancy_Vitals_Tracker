package com.android.pregnancyvitalstracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "items")
data class LocalItem(
    @PrimaryKey(autoGenerate = true)

    val itemId: Int = 0,
    val bloodPressure: String,
    val hartRate: String,
    val weight: String,
    val babyKicksCount: String,
    val dateTime: LocalDateTime = LocalDateTime.now()
)