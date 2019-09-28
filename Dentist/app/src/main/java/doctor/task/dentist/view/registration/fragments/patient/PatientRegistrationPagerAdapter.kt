package doctor.task.dentist.view.registration.fragments.patient

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import doctor.task.dentist.view.BlankFragment
import doctor.task.dentist.view.registration.fragments.patient.fragments.PatientLoginFragment
import doctor.task.dentist.view.registration.fragments.patient.fragments.PatientSignupFragment

@Suppress("DEPRECATION")
class PatientRegistrationPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> PatientLoginFragment()
            1 -> PatientSignupFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}