package doctor.task.dentist.view.features.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.AdapterToViewCallback
import doctor.task.dentist.base.extensions.logOut
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.models.Patient
import doctor.task.dentist.view.features.doctor.adapters.PatientAdapter
import doctor.task.dentist.view.features.doctor.patientFile.PatientFileActivity
import kotlinx.android.synthetic.main.activity_reservation_list.*

class ReservationListActivity : AppCompatActivity(), AdapterToViewCallback {

    val adapter = PatientAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        //TODO:: load patients
        setDummyData()
        initView()
    }

    private fun initView() {
        patients_recyclerview.layoutManager = LinearLayoutManager(this)
        patients_recyclerview.adapter = adapter

        logout.setOnClickListener {
            logOut(this)
            finishAffinity()
        }
    }

    override fun openActivity() {
        openActivity(this,PatientFileActivity::class.java)
    }

    fun setDummyData(){
        val patients_list = arrayListOf<Patient>()
        patients_list.add(Patient())
        patients_list.add(Patient())
        patients_list.add(Patient())

        adapter.setData(patients_list)
    }
}
