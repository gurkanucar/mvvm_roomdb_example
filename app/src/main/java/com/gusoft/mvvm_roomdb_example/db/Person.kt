package com.gusoft.mvvm_roomdb_example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "person_name")
    val name: String,
    @ColumnInfo(name = "person_surname")
    val surname: String,
    @ColumnInfo(name = "phone_number")
    val phone: String
)
