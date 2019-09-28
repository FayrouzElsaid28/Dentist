package doctor.task.dentist.base.helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * Determining launching application to specify which activity to start with
 */
class LaunchingActivity private constructor(){

    fun setIsFirstLaunchToFalse() {
        editor!!.putBoolean(IS_FIRST_LAUNCH, false)
        editor!!.commit()
    }

    fun isFirstLaunch() : Boolean {
        return sharedPreferences!!.getBoolean(
            IS_FIRST_LAUNCH, true)
    }

    companion object {
        private val sharedPref = LaunchingActivity()
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private val IS_FIRST_LAUNCH = "is_first_launch"

        @Synchronized
        fun getInstance(context: Context): LaunchingActivity {

            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                editor = sharedPreferences!!.edit()
            }

            return sharedPref
        }
    }
}

