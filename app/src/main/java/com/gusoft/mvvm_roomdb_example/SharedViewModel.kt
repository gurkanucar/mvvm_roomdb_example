package com.gusoft.mvvm_roomdb_example

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gusoft.mvvm_roomdb_example.db.models.Person
import com.gusoft.mvvm_roomdb_example.db.PersonRepository
import com.gusoft.mvvm_roomdb_example.db.models.Intimacy
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SharedViewModel(application: Application) : AndroidViewModel(application) {

    /** ============================= List Fragment ============================= */

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(toDoData: List<Person>) {
        emptyDatabase.value = toDoData.isEmpty()
    }

    /** ============================= Add/Update Fragment ============================= */

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
//            when(position){
//                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, "#fff")) }
//                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
//                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
//            }
        }
    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parseIntimacy(intimacy: String): Intimacy {
        return when (intimacy) {
            "Family" -> {
                Intimacy.FAMILY
            }
            "Friend" -> {
                Intimacy.FRIEND
            }
            "Job" -> {
                Intimacy.JOB
            }
            else -> Intimacy.OTHER
        }
    }

}