package doctor.task.dentist.view.features.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import doctor.task.dentist.api.DoctorFactory
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.models.Patient.Patient

object DoctorRepository {

    fun getAllPatients(): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        DoctorFactory.getAllPatients()
            .getAsObject(Patient::class.java, object : ParsedRequestListener<Patient>{
                override fun onResponse(response: Patient?) {
                    if (response?.code == 200){
                        if (response.result.size > 0){
                            DoctorHelper.patientList = response.result
                            data.postValue(Resource.success("success"))
                        }else{
                            data.postValue(Resource.empty("",""))
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                    data.postValue(Resource.error(anError?.errorBody))
                }

            })

        return data
    }
}