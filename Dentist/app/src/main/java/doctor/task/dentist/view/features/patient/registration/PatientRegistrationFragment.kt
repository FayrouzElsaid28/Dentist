package doctor.task.dentist.view.features.patient.registration


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import kotlinx.android.synthetic.main.fragment_patient_registration.*

class PatientRegistrationFragment : Fragment(), INavigation {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleViewPager()
    }

    override fun handleViewPager() {
        pateirnt_registration_viewpager.adapter =
            PatientRegistrationPagerAdapter(
                childFragmentManager
            )
    }

}
