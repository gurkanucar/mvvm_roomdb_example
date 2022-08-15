package com.gusoft.mvvm_roomdb_example.db

class PersonRepository(private val dao: PersonDAO) {

    val persons = dao.getAllPersons()


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