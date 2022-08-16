package com.gusoft.mvvm_roomdb_example.db.converter

import androidx.room.TypeConverter
import com.gusoft.mvvm_roomdb_example.db.models.Intimacy

class Converter {

    @TypeConverter
    fun fromIntimacy(intimacy: Intimacy): String {
        return intimacy.name
    }

    @TypeConverter
    fun toIntimacy(intimacy: String): Intimacy {
        return Intimacy.valueOf(intimacy)
    }

}