package doctor.task.dentist.view.registration.fragments.doctor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import doctor.task.dentist.view.BlankFragment
import doctor.task.dentist.view.registration.fragments.doctor.fragments.DoctorLoginFragment
import doctor.task.dentist.view.registration.fragments.doctor.fragments.DoctorSignupFragment

@Suppress("DEPRECATION")
class DoctorRegistrationPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DoctorLoginFragment()
            1 -> DoctorSignupFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}