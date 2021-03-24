package com.example.materialcontainertransformplayground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materialcontainertransformplayground.databinding.FragmentMyBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialog : BottomSheetDialogFragment() {

    private var binding: FragmentMyBottomSheetDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBottomSheetDialogBinding.inflate(inflater)
        binding!!.edt.requestFocus()
        return binding!!.root
    }
}