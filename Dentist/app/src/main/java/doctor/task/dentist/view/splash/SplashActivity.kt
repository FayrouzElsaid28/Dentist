package doctor.task.dentist.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.getSharedPreferences
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.base.helpers.LaunchingActivity
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.features.patient.MainActivity
import doctor.task.dentist.view.registration.RegistrationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userType = getUserType()

        //Open another activity after one second
        Handler().postDelayed({
            when(userType){
                "Doctor" -> openActivity(this, ReservationListActivity::class.java)
                "Patient" -> openActivity(this,MainActivity::class.java)
                else -> openActivity(this, RegistrationActivity::class.java)
            }
            finishAffinity()
        }, 1000)
    }

    fun getUserType(): String{
        if (getSharedPreferences().contains("user"))
            return getSharedPreferences().getString("user","null")!!
        else
            return "null"
    }
}
