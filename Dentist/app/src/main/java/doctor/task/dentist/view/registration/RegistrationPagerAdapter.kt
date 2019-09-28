package doctor.task.dentist.view.registration

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import doctor.task.dentist.view.BlankFragment
import doctor.task.dentist.view.registration.fragments.doctor.DoctorRegistrationFragment
import doctor.task.dentist.view.registration.fragments.patient.PatientRegistrationFragment

@Suppress("DEPRECATION")
class RegistrationPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DoctorRegistrationFragment()
            1 -> PatientRegistrationFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}