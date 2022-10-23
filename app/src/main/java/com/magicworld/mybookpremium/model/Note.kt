package com.magicworld.mybookpremium.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magicworld.mybookpremium.model.MyColors.*
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val color:Long = White.color
):Parcelable
