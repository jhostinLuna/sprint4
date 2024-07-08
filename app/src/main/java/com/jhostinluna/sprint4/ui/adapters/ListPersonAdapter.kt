package com.jhostinluna.sprint4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jhostinluna.sprint4.databinding.ItemPersonRecyclerviewBinding
import com.jhostinluna.sprint4.domain.model.person.PersonModel

class ListPersonAdapter: RecyclerView.Adapter<ListPersonAdapter.PersonViewHolder>() {
    private var persons: List<PersonModel> = emptyList()

    var onClickListener: (id: Int) -> Unit = {}
    fun setListPerson(personList: List<PersonModel>){
        this.persons = personList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonRecyclerviewBinding.inflate(layoutInflater,parent,false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.person = persons[position]
        holder.bind()
        holder.initListeners()
    }
    inner class PersonViewHolder(private val binding: ItemPersonRecyclerviewBinding): ViewHolder(binding.root) {
        var person:PersonModel? = null
        fun bind() {
            binding.textViewName.text = person?.name
        }
        fun initListeners() {
            binding.root.setOnClickListener {
                person?.id?.let {id ->
                    onClickListener(id)
                }
            }
        }
    }
}