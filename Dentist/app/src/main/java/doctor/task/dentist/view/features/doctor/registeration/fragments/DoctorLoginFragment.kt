package doctor.task.dentist.view.features.doctor.registeration.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import doctor.task.dentist.base.extensions.getSharedPreferences
import doctor.task.dentist.base.extensions.openActivtyFromParent
import doctor.task.dentist.base.extensions.set
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.registration.IRegistration
import kotlinx.android.synthetic.main.fragment_doctor_login.*
import kotlinx.android.synthetic.main.fragment_doctor_registration.*

class DoctorLoginFragment : Fragment(),
    INavigation,
    IRegistration{

    var parentViewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        parentViewPager = activity?.supportFragmentManager?.fragments?.get(0)?.doctor_registration_viewpager
        doctor_login_create_account.setOnClickListener {
            fragmentSwitch(1)
        }
        doctor_login_btn.setOnClickListener {
            //Set user to doctor
            setUserType()
            openActivtyFromParent(ReservationListActivity::class.java)
            activity?.finishAffinity()
        }
    }

    //Switch to doctor sign up fragment
    override fun fragmentSwitch(id: Int) {
        parentViewPager?.setCurrentItem(id)
    }

    override fun setUserType() {
        getSharedPreferences().set("user","Doctor")
    }

}
