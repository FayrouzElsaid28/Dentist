package doctor.task.dentist.view.features.patient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import doctor.task.dentist.api.PatientFactory
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.models.doctor.Doctor

object PatientRepository {

    fun getDoctorsBySpecialty(clinic: String,
                      specify: String): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        PatientFactory.getDoctorsBySpecialty(clinic,specify)
            .getAsObjectList(Doctor::class.java, object : ParsedRequestListener<ArrayList<Doctor>>{
                override fun onResponse(response: ArrayList<Doctor>?) {
                    if (response?.size!! > 0){
                        PatientHelper.doctorsList = response
                        data.postValue(Resource.success(""))
                    }else{
                        data.postValue(Resource.empty("",""))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.d("error",anError?.errorBody)
                    data.postValue(Resource.error(anError?.errorBody))
                }

            })

        return data
    }

    fun getDoctorInfo(): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        //TODO:: get info

        return data
    }
}