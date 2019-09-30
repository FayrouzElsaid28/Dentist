package doctor.task.dentist.view.features.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.getClinic
import doctor.task.dentist.base.extensions.logOut
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.view.features.patient.clinic.ClinicDetailsActivity
import doctor.task.dentist.view.features.patient.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        notification_img.setOnClickListener {
            openActivity(this,NotificationActivity::class.java)
        }
        logout.setOnClickListener {
            logOut(this)
            finishAffinity()
        }

        clinic1.setOnClickListener {
            setClinic("clinic1")
        }
        clinic2.setOnClickListener {
            setClinic("clinic2")
        }
        clinic3.setOnClickListener {
            setClinic("clinic3")
        }
        clinic4.setOnClickListener {
            setClinic("clinic4")
        }

    }

    private fun setClinic(clinicName: String){
        PatientHelper.clinic = clinicName
        openActivity(this,ClinicDetailsActivity::class.java)
    }
}
