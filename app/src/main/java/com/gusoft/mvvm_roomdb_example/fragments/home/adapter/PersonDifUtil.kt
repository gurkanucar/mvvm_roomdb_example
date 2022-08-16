package com.gusoft.mvvm_roomdb_example.fragments.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gusoft.mvvm_roomdb_example.db.models.Person

class PersonDifUtil(
    private val oldList: List<Person>,
    private val newList: List<Person>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].surname == newList[newItemPosition].surname
                && oldList[oldItemPosition].intimacy == newList[newItemPosition].intimacy
                && oldList[oldItemPosition].phone == newList[newItemPosition].phone
    }
}