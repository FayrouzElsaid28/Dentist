package doctor.task.dentist.view.features.patient.clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.view.features.patient.adapters.ClinicDatesAdapter
import doctor.task.dentist.view.features.patient.adapters.ClinicNumbersAdapter
import doctor.task.dentist.view.features.patient.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_clinic_details.*

class ClinicDetailsActivity : AppCompatActivity() {

    val clinicDatesAdapter = ClinicDatesAdapter()
    val clinicNumbersAdapter = ClinicNumbersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic_details)

        // TODO:: get dates and numbers -- Set clinic data

        initView()
    }

    private fun initView() {
        clinic_dates_recyclerview.layoutManager = LinearLayoutManager(this)
        clinic_numbers_recyclerview.layoutManager = LinearLayoutManager(this)

        //Set with adapters
        clinic_dates_recyclerview.adapter = clinicDatesAdapter
        clinic_numbers_recyclerview.adapter = clinicNumbersAdapter

        back.setOnClickListener { onBackPressed() }
        notification_img.setOnClickListener {
            openActivity(this,NotificationActivity::class.java)
        }
        clinic_location.setOnClickListener {
            //TODO:: open location on map
        }
    }
}
