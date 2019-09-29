package doctor.task.dentist.view.features.doctor.patientFile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import doctor.task.dentist.R
import doctor.task.dentist.models.Patient
import kotlinx.android.synthetic.main.activity_patient_file.*

class PatientFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_file)

        initView()
        getPatientData()
    }

    private fun initView() {
        back.setOnClickListener { onBackPressed() }
        //TODO:: disease adapter and list
    }

    private fun getPatientData() {
        patient_name.text = PatientDetails.patient_name
        patient_age.text = PatientDetails.age.toString()
    }
}
