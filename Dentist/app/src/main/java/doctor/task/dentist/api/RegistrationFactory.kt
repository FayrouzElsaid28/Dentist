package doctor.task.dentist.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.concurrent.TimeUnit

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

        return AndroidNetworking.upload(DOCTOR_REGISTER_URL)
            .addQueryParameter("fullName",fullName)
            .addMultipartFile("image",image)
            .addQueryParameter("identityNumber",identityNumber)
            .addQueryParameter("password",password)
            .addQueryParameter("phone",phone)
            .addQueryParameter("specify",specify)
            .addQueryParameter("clinic",clinic)
            .addQueryParameter("cost",cost)
            .addQueryParameter("workingTime",workingTime.toString())
            .setOkHttpClient(
                OkHttpClient().newBuilder()
                    .readTimeout(10000, TimeUnit.SECONDS)
                    .connectTimeout(10000, TimeUnit.SECONDS)
                    .writeTimeout(100000, TimeUnit.SECONDS)
                    .build())
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