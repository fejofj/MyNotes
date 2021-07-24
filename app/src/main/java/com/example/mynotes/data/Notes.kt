package com.example.mynotes.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "user_notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val date:Long,
    val notes:String
):Parcelable
