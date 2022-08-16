package com.gusoft.mvvm_roomdb_example.db.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "person_table")
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "person_name")
    val name: String,
    @ColumnInfo(name = "person_surname")
    val surname: String,
    @ColumnInfo(name = "phone_number")
    val phone: String,
    val intimacy: Intimacy
) : Parcelable
