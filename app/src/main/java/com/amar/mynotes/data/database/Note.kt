package com.amar.mynotes.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
     @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
     @ColumnInfo(name = "title") val title: String,
     @ColumnInfo(name = "description") val description: String
) : Parcelable