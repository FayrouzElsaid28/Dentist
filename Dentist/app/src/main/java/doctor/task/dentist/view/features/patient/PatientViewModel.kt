package doctor.task.dentist.view.features.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import doctor.task.dentist.base.helpers.Resource

class PatientViewModel: ViewModel() {
    fun getDoctorsBySpecialty(clinic: String,
                              specify: String): LiveData<Resource<String>> {
        return PatientRepository.getDoctorsBySpecialty(
            clinic,
            specify
        )
    }
}