package com.gusoft.mvvm_roomdb_example.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gusoft.mvvm_roomdb_example.databinding.PersonListItemBinding
import com.gusoft.mvvm_roomdb_example.db.models.Person

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.MyViewHolder>() {

    var dataList = emptyList<Person>()

    class MyViewHolder(private val binding: PersonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(personData: Person) {
            binding.personData = personData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PersonListItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(personData: List<Person>) {
        val personDifUtil = PersonDifUtil(dataList, personData)
        val personDifResult = DiffUtil.calculateDiff(personDifUtil)
        this.dataList = personData
        personDifResult.dispatchUpdatesTo(this)
    }

}