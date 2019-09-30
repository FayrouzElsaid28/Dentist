package doctor.task.dentist.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

object RegistrationFactory {

    lateinit var token: String

    //Patient register
    fun registerPatient(fullName: String,
                        identityNumber: String,
                        password: String,
                        age: String,
                        patientHistory: JSONArray): ANRequest<*> {

        val registerPatientJson = JSONObject()
        registerPatientJson.put("fullName",fullName)
        registerPatientJson.put("identityNumber",identityNumber)
        registerPatientJson.put("password",password)
        registerPatientJson.put("age",age)
        registerPatientJson.put("patientHistory",patientHistory)

        return AndroidNetworking.post(PATIENT_REGISTER_URL)
            .addJSONObjectBody(registerPatientJson)
            .setPriority(Priority.MEDIUM)
            .build()
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

        return AndroidNetworking.post(DOCTOR_REGISTER_URL)
            .addBodyParameter("fullName",fullName)
            .addBodyParameter(image)
            .addBodyParameter("identityNumber",identityNumber)
            .addBodyParameter("password",password)
            .addBodyParameter("phone",phone)
            .addBodyParameter("specify",specify)
            .addBodyParameter("clinic",clinic)
            .addBodyParameter("cost",cost)
            .addBodyParameter("workingTime",workingTime.toString())
            .setPriority(Priority.MEDIUM)
            .build()
    }

    //Login
    fun login(identityNumber:String,
              password: String): ANRequest<*> {

        return AndroidNetworking.post(LOGIN_URL)
            .addBodyParameter("identityNumber",identityNumber)
            .addBodyParameter("password",password)
            .setPriority(Priority.MEDIUM)
            .build()
    }
}