package doctor.task.dentist.view.features.doctor.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R
import doctor.task.dentist.base.AdapterToViewCallback
import doctor.task.dentist.models.Patient.PatientHistory
import doctor.task.dentist.models.Patient.Result
import doctor.task.dentist.view.features.doctor.DoctorHelper
import doctor.task.dentist.view.features.doctor.patientFile.PatientDetails
import org.json.JSONObject

class PatientAdapter(private val callback: AdapterToViewCallback):
    RecyclerView.Adapter<PatientAdapter.ViewHolder>() {

    var patient_list = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_patient,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return patient_list.size
    }

    fun setData(newData: List<Result>){
        patient_list = newData
        notifyDataSetChanged()
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient_name = patient_list[position].fullName
        val patient_age = patient_list[position].age
        var diseaseList = patient_list[position].patientHistory

        holder.patient_day.text = patient_list[position].day
        holder.patient_name.text = patient_name
        holder.patient_hour.text = patient_list[position].hour
        if (position % 2 == 1)
            holder.item_patient.setBackgroundResource(R.color.white)
        else
            holder.item_patient.setBackgroundResource(R.color.patient_color)

        holder.item_patient.setOnClickListener {
            setPatientData(patient_name,patient_age,diseaseList)
            callback.openActivity()
        }
    }

    private fun setPatientData(
        patientName: String,
        patientAge: Int,
        diseaseList: List<PatientHistory>
    ) {
        PatientDetails.age = patientAge
        PatientDetails.patient_name = patientName

        for (disease in diseaseList){
            val json = JSONObject()
            json.put("disease",disease.disease)
            json.put("date",disease.date)

            DoctorHelper.diseaseList.put(json)
        }
    }

    /* private fun setPatientData(patientName: String, patientAge: Int) {
         PatientDetails.age = patientAge
         PatientDetails.patient_name = patientName
     }*/


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_patient = itemView.findViewById<LinearLayout>(R.id.item_patient)
        var patient_hour = itemView.findViewById<TextView>(R.id.patient_hour)
        var patient_day = itemView.findViewById<TextView>(R.id.patient_day)
        var patient_name = itemView.findViewById<TextView>(R.id.patient_name)
    }
}