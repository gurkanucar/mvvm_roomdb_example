package com.gusoft.mvvm_roomdb_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gusoft.mvvm_roomdb_example.db.models.Intimacy
import com.gusoft.mvvm_roomdb_example.db.models.Person
import com.gusoft.mvvm_roomdb_example.db.viewmodel.PersonViewModel

class MainActivity : AppCompatActivity() {


    private val mToDoViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mToDoViewModel.insertData(Person(0, "Gurkan", "UCAR", "123456789", Intimacy.OTHER))
//        mToDoViewModel.insertData(Person(0, "Ahmet", "CAKAR", "234234", Intimacy.FAMILY))
//        mToDoViewModel.insertData(Person(0, "Mehmet", "ALEV", "345354", Intimacy.JOB))
//        mToDoViewModel.insertData(Person(0, "Ayse", "DURSUN", "65756756", Intimacy.FRIEND))
//        mToDoViewModel.insertData(Person(0, "Fatma", "CELIK", "345345345345", Intimacy.FRIEND))
//        mToDoViewModel.insertData(Person(0, "Hayriye", "SOLMAZ", "234234234", Intimacy.OTHER))

    }
}