package com.gusoft.mvvm_roomdb_example.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gusoft.mvvm_roomdb_example.db.AppDatabase
import com.gusoft.mvvm_roomdb_example.db.PersonRepository
import com.gusoft.mvvm_roomdb_example.db.models.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val personDao = AppDatabase.getDatabase(
        application
    ).personDAO()
    private val repository: PersonRepository = PersonRepository(personDao)

    val getAllData: LiveData<List<Person>> = repository.getAllData
//    val sortByHighPriority: LiveData<List<ToDoData>> = repository.sortByHighPriority
//    val sortByLowPriority: LiveData<List<ToDoData>> = repository.sortByLowPriority

    fun insertData(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(person)
        }
    }

    fun updateData(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(person)
        }
    }

    fun deleteItem(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(person)
        }
    }

//    fun deleteAll() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAll()
//        }
//    }

//    fun searchDatabase(searchQuery: String): LiveData<List<Person>> {
//        return repository.searchDatabase(searchQuery)
//    }

}