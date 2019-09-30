package doctor.task.dentist.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import doctor.task.dentist.view.registration.RegistrationRepository

object PatientFactory {

    //Get doctors by specialty
    fun getDoctorsBySpecialty(clinic: String,
                      specify: String): ANRequest<*> {

        Log.d("clinic",clinic)
        Log.d("specify",specify)
        Log.d("token", RegistrationRepository.token)

        return AndroidNetworking.get(DOCTORS_URL)
            .addQueryParameter("token",RegistrationRepository.token)
            .addQueryParameter("clinic",clinic)
            .addQueryParameter("specify",specify)
            .build()
    }

    //Get doctor information
    fun getDoctorInfo(): ANRequest<*> {
        return AndroidNetworking.get(DOCTOR_INFO_URL)
            .addQueryParameter("token",RegistrationRepository.token)
            .build()
    }

    //Make reservation
    fun makeReservation(date: String): ANRequest<*> {
        return AndroidNetworking.post(MAKE_RESERVATION)
            .addBodyParameter("token",RegistrationRepository.token)
            .addBodyParameter("date",date)
            .build()
    }
}