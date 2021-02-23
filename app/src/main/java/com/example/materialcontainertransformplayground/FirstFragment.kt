package com.example.materialcontainertransformplayground

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialcontainertransformplayground.R.id.recycler
import com.example.materialcontainertransformplayground.databinding.FragmentFirstBinding
import com.google.android.material.transition.Hold

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.transitionName = "shared_element_button"
        binding.buttonFirst.setOnClickListener {
            (parentFragment as RootFragment).navigate(
                this,
                SecondFragment(),
                sharedElements = listOf(Pair(binding.buttonFirst, binding.buttonFirst.transitionName))
            )
        }
        binding.buttonThird.setOnClickListener {
            (parentFragment as RootFragment).navigate(
                this,
                ThirdFragment()
            )
        }

        val data = mutableListOf<String>()
        for (x in 1..50) {
            data.add(x.toString())
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = RAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("eeeeeee", "ondestroyview")
        binding.recycler.adapter = null
        _binding = null
    }

    class RAdapter(val data: List<String>) : RecyclerView.Adapter<RAdapter.RHolder>() {

        class RHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView

            init {
                textView = view.findViewById(R.id.text)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
            return RHolder(view)
        }

        override fun onBindViewHolder(holder: RHolder, position: Int) {
            holder.textView.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}