package doctor.task.dentist.view.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import doctor.task.dentist.base.helpers.Resource
import org.json.JSONArray
import java.io.File

class RegistrationViewModel: ViewModel() {

    fun registerDoctor(fullName: String,
                       image: File,
                       identityNumber: String,
                       password: String,
                       phone: String,
                       specify: String,
                       clinic: String,
                       cost: String,
                       workingTime: JSONArray
    ): LiveData<Resource<String>> {
        return RegistrationRepository.registerDoctor(
            fullName,
            image,
            identityNumber,
            password,
            phone,
            specify,
            clinic,
            cost,
            workingTime
        )
    }

    fun registerPatient(fullName: String,
                        identityNumber: String,
                        password: String,
                        age: String,
                        patientHistory: JSONArray): LiveData<Resource<String>> {
        return RegistrationRepository.registerPatient(
            fullName,
            identityNumber,
            password,
            age,
            patientHistory
        )
    }

    fun login(identityNumber:String,
              password: String): LiveData<Resource<String>> {
        return RegistrationRepository.login(
            identityNumber,
            password
        )
    }
}