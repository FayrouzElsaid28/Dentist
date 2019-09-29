package doctor.task.dentist.view.features.patient.clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.view.features.patient.adapters.DoctorAdapter
import doctor.task.dentist.view.features.patient.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_specialty.*

class SpecialtyActivity : AppCompatActivity() {

    val adapter = DoctorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialty)

        //TODO:: get all doctors
        initView()
    }

    private fun initView() {
        doctors_recyclerview.layoutManager = LinearLayoutManager(this)
        doctors_recyclerview.adapter = adapter
        //TODO:: Set adapter data

        back.setOnClickListener { onBackPressed() }
        notification_img.setOnClickListener {
            openActivity(this,NotificationActivity::class.java)
        }
    }
}
