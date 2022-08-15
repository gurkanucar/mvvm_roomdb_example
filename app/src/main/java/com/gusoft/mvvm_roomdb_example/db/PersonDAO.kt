package com.gusoft.mvvm_roomdb_example.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person): Long

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("select * from person_table")
    fun getAllPersons(): LiveData<List<Person>>

    @Query("select * from person_table WHERE person_name  LIKE :search OR person_surname LIKE :search")
    fun searchPersonByName(search: String): LiveData<List<Person>>

}