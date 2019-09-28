package doctor.task.dentist.view.registration.fragments.patient.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.openActivtyFromParent
import doctor.task.dentist.view.features.patient.MainActivity
import kotlinx.android.synthetic.main.fragment_patient_login.*

class PatientLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO:: For test remove it later
        patient_login_btn.setOnClickListener {
            openActivtyFromParent(MainActivity::class.java)
        }
    }
}
