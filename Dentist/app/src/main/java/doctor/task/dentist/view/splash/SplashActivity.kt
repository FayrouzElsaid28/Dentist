package doctor.task.dentist.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.base.helpers.LaunchingActivity
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.registration.RegistrationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Open another activity after one second
        Handler().postDelayed({
            //TODO:: Check if user is patient or doctor and remove this
            openActivity(this, RegistrationActivity::class.java)
            finishAffinity()
        }, 1000)
    }
}
