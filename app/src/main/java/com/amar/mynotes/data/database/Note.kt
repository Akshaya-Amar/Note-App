package com.amar.mynotes.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amar.mynotes.utils.AppUtils
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
     @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
     @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis(),
     @ColumnInfo(name = "title") val title: String,
     @ColumnInfo(name = "description") val description: String,
     @ColumnInfo(name = "date_time") val currentDateTime: String = AppUtils.getFormattedDateTime()
) : Parcelable