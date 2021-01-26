package com.example.materialcontainertransformplayground

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform

class RootFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigate(FirstFragment())
    }

    fun navigate(newFragment: Fragment, sharedElements: List<Pair<View, String>> = emptyList()) {
        val fragmentTag = newFragment::class.java.simpleName

        val transaction = childFragmentManager
            .beginTransaction()
            .addToBackStack(fragmentTag)

        if (sharedElements.isNotEmpty()) {
            sharedElements.forEach {
                transaction.addSharedElement(it.first, it.second)
            }

            val transition = MaterialContainerTransform().apply {
                duration = 1000L
                //isDrawDebugEnabled = true
                scrimColor = Color.TRANSPARENT
            }
            newFragment.sharedElementEnterTransition = transition
            newFragment.sharedElementReturnTransition = transition
        }

        transaction
            .replace(R.id.container_view, newFragment, fragmentTag)
            .setReorderingAllowed(true)
            .commitAllowingStateLoss()
    }
}