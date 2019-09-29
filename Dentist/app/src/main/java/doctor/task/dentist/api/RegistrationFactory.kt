package doctor.task.dentist.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority
import org.json.JSONArray
import java.io.File

object RegistrationFactory {

    lateinit var token: String

    //Patient register
    fun registerPatient(fullName: String,
                        identityNumber: String,
                        password: String,
                        age: String,
                        patientHistory: JSONArray): ANRequest<*> {
        val androidNetworking = AndroidNetworking.post(PATIENT_REGISTER_URL)
            .addBodyParameter("fullName",fullName)
            .addBodyParameter("identityNumber",identityNumber)
            .addBodyParameter("password",password)
            .addBodyParameter("age",age)
            .addBodyParameter(patientHistory)
            .setPriority(Priority.MEDIUM)
            .build()

        return androidNetworking
    }

    //Doctor register
    fun registerDoctor(fullName: String,
                       image: File,
                       identityNumber: String,
                       password: String,
                       phone: String,
                       specify: String,
                       clinic: String,
                       cost: String,
                       workingTime: JSONArray): ANRequest<*> {
        val androidNetworking = AndroidNetworking.post(DOCTOR_REGISTER_URL)
            .addBodyParameter("fullName",fullName)
            .addBodyParameter(image)
            .addBodyParameter("identityNumber",identityNumber)
            .addBodyParameter("password",password)
            .addBodyParameter("phone",phone)
            .addBodyParameter("specify",specify)
            .addBodyParameter("clinic",clinic)
            .addBodyParameter("cost",cost)
            .addBodyParameter(workingTime)
            .setPriority(Priority.MEDIUM)
            .build()

        return androidNetworking
    }

    //Login
    fun login(identityNumber:String,
              password: String): ANRequest<*> {
        val androidNetworking = AndroidNetworking.post(LOGIN_URL)
            .addBodyParameter("identityNumber",identityNumber)
            .addBodyParameter("password",password)
            .setPriority(Priority.MEDIUM)
            .build()

        return androidNetworking
    }
}