package com.example.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class TaskToDo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val timeAdded: String,
    val desc: String,
    val done: Int
        ): Parcelable
