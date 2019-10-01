package doctor.task.dentist.view.features.doctor

import doctor.task.dentist.models.Patient.Patient
import doctor.task.dentist.models.Patient.Result
import org.json.JSONArray

class DoctorHelper {
    companion object{
        var patientList = listOf<Result>()
        var patientName = ""
        var patientAge = 0
        var diseaseList = JSONArray()
    }
}