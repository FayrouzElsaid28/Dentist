package doctor.task.dentist.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import doctor.task.dentist.view.registration.RegistrationRepository

object DoctorFactory {

    //Get all patients
    fun getAllPatients(): ANRequest<*> {
        return AndroidNetworking.get(GET_PATIENTS_URL)
            .addQueryParameter("token", RegistrationRepository.token)
            .build()
    }
}