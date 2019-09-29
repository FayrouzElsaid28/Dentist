package doctor.task.dentist.view.features.doctor.registeration


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import kotlinx.android.synthetic.main.fragment_doctor_registration.*

class DoctorRegistrationFragment : Fragment(), INavigation {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleViewPager()
    }

    override fun handleViewPager() {
        doctor_registration_viewpager.adapter =
            DoctorRegistrationPagerAdapter(
                childFragmentManager
            )
    }

}
