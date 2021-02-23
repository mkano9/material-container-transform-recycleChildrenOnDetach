package com.example.materialcontainertransformplayground

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
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

        navigate(this, FirstFragment())
    }

    fun pop() {
        childFragmentManager.popBackStack()
    }

    fun navigate(source: Fragment, newFragment: Fragment, sharedElements: List<Pair<View, String>> = emptyList()) {
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

            source.exitTransition = Hold().apply {
                duration = 1000L
            }
        } else {
            transaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in_pop_enter,
                R.anim.slide_out_pop_exit
            )
            source.exitTransition = null
        }

        transaction
            .replace(R.id.container_view, newFragment, fragmentTag)
            .setReorderingAllowed(true)
            .commitAllowingStateLoss()
    }
}