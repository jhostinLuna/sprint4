package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentHomeBinding
import com.jhostinluna.sprint4.databinding.ItemPersonRecyclerviewBinding
import com.jhostinluna.sprint4.ui.adapters.ListPersonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var personListAdapter: ListPersonAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personListAdapter = ListPersonAdapter()
    }

    override fun inflateBinding() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        setListeners()
    }

    override fun observeViewModel() {
        initializeViews()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.personListMutableStatFlow.collect {personList->
                personListAdapter.setListPerson(personList)
                personListAdapter.notifyDataSetChanged()
            }
        }
        personListAdapter.onClickListener = {id->
            findNavController().navigate(route = "detail/$id")
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {

    }

    override fun configureToolbarAndConfigScreenSections() {

    }
    private fun setListeners() {
        binding?.buttonAdd?.setOnClickListener {
            findNavController().navigate(route = "create")
        }


    }
    fun initializeViews() {
        binding?.personRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = personListAdapter
        }
    }

}