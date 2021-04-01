package com.example.materialcontainertransformplayground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            navigate(RootFragment())
        }
    }

    fun pop() {
        supportFragmentManager.popBackStack()
    }

    fun navigate(newFragment: Fragment) {
        val fragmentTag = newFragment::class.java.simpleName

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragmentTag)
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in_pop_enter,
                R.anim.slide_out_pop_exit
            )
            .replace(R.id.nav_host_fragment, newFragment, fragmentTag)
            .setReorderingAllowed(true)
            .commitAllowingStateLoss()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigate(SecondFragment())
    }
}