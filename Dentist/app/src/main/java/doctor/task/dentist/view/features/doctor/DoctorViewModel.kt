package doctor.task.dentist.view.features.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import doctor.task.dentist.base.helpers.Resource

class DoctorViewModel: ViewModel(){
    fun getAllPatients(): LiveData<Resource<String>> {
        return DoctorRepository.getAllPatients()
    }
}