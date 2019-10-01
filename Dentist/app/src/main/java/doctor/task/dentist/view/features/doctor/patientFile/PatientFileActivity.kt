package doctor.task.dentist.view.features.doctor.patientFile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.selectTime
import doctor.task.dentist.view.features.doctor.DoctorHelper
import doctor.task.dentist.view.features.patient.adapters.DiseaseAdapter
import kotlinx.android.synthetic.main.activity_patient_file.*
import org.json.JSONObject

class PatientFileActivity : AppCompatActivity() {

    val adapter = DiseaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_file)

        initView()
        getPatientData()
    }

    private fun initView() {
        disease_recyclerview.layoutManager = LinearLayoutManager(this)
        disease_recyclerview.adapter = adapter
        adapter.setDate(DoctorHelper.diseaseList)

        patient_disease_date_et.setOnClickListener {
            selectTime(patient_disease_date_et)
        }

        back.setOnClickListener { onBackPressed() }
        patient_add_disease_layout.setOnClickListener {
            val json = JSONObject()
            json.put("disease",patient_disease_name_et.text.toString())
            json.put("date",patient_disease_date_et.text.toString())

            DoctorHelper.diseaseList.put(json)
            adapter.notifyDataSetChanged()
        }
    }

    private fun getPatientData() {
        patient_name.text = PatientDetails.patient_name
        patient_age.text = PatientDetails.age.toString()
    }
}
