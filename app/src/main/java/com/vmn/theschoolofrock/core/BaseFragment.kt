package com.vmn.theschoolofrock.core

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.vmn.theschoolofrock.MainActivity

/**
 * abstract base class for common operations.
 */
abstract class BaseFragment : Fragment() {

    fun showShortMessage(str: String?) {
        if (!str.isNullOrBlank()) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
    }

    fun hideBottomNavigationView() {
        (activity as? MainActivity)?.showBottomNavigation(false)
    }

    fun showBottomNavigationView() {
        (activity as? MainActivity)?.showBottomNavigation(true)
    }

    /**
     * To handle back in case of some fragments
     */
    fun onBackPressed(block: () -> Unit) {
        activity?.onBackPressedDispatcher?.addCallback {
            block()
        }
    }

}