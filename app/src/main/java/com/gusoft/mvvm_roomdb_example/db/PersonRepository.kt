package com.gusoft.mvvm_roomdb_example.db

import androidx.lifecycle.LiveData
import com.gusoft.mvvm_roomdb_example.db.models.Person

class PersonRepository(private val dao: PersonDAO) {

    val getAllData: LiveData<List<Person>> = dao.getAllPersons()


    suspend fun insert(person: Person) {
        dao.insertPerson(person)
    }

    suspend fun update(person: Person) {
        dao.updatePerson(person)
    }

    suspend fun delete(person: Person) {
        dao.deletePerson(person)
    }


}