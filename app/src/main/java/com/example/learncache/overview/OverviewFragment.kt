package com.example.learncache.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learncache.R
import com.example.learncache.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private lateinit var viewModel : OverviewViewModel
    private lateinit var binding : FragmentOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel


        //add recyclerview
        val viewAdapter = ItemAdapter()
        binding.recyclerView.adapter = viewAdapter

        viewModel.items.observe(viewLifecycleOwner, Observer { list->
            viewAdapter.submitList(list)
        })

        return binding.root
    }


}