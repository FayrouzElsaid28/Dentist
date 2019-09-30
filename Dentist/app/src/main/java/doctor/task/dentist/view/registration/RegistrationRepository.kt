package doctor.task.dentist.view.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import doctor.task.dentist.api.RegistrationFactory
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.models.register.Register
import org.json.JSONArray
import java.io.File

object RegistrationRepository {

    lateinit var token: String

    fun registerPatient(fullName: String,
                        identityNumber: String,
                        password: String,
                        age: String,
                        patientHistory: JSONArray): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        RegistrationFactory.registerPatient(
            fullName,
            identityNumber,
            password,
            age,
            patientHistory
            ).getAsObject(
            Register::class.java, object : ParsedRequestListener<Register>{
                override fun onResponse(response: Register?) {
                   if (response?.code == 200){
                       data.postValue(Resource.success(response.result.token))
                   }else{
                       data.postValue(Resource.error(response?.error?.toString()))
                   }
                }

                override fun onError(anError: ANError?) {
                    Log.d("error", anError?.errorBody)
                    data.postValue(Resource.error("Something went wrong"))
                }
            })

        return data
    }

    fun registerDoctor(fullName: String,
                       image: File,
                       identityNumber: String,
                       password: String,
                       phone: String,
                       specify: String,
                       clinic: String,
                       cost: String,
                       workingTime: JSONArray): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        RegistrationFactory.registerDoctor(
            fullName,
            image,
            identityNumber,
            password,
            phone,
            specify,
            clinic,
            cost,
            workingTime
        ).getAsObject(
            Register::class.java, object : ParsedRequestListener<Register>{
                override fun onResponse(response: Register?) {
                    if (response?.code == 200){
                        data.postValue(Resource.success(response.result.token))
                    }else{
                        data.postValue(Resource.error(response?.error?.toString()))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.d("error", anError?.errorBody)
                    data.postValue(Resource.error("Something went wrong"))
                }
            })

        return data
    }

    fun login(identityNumber:String,
              password: String): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        RegistrationFactory.login(
            identityNumber,
            password
        ).getAsObject(Register::class.java, object : ParsedRequestListener<Register>{
            override fun onResponse(response: Register?) {
                if (response?.code == 200){
                    data.postValue(Resource.success(response.result.token))
                }else{
                    data.postValue(Resource.error(response?.error?.toString()))
                }
            }

            override fun onError(anError: ANError?) {
                Log.d("error",anError?.errorBody)
                data.postValue(Resource.error("Wrong password or id"))
            }

        })

        return data
    }
}