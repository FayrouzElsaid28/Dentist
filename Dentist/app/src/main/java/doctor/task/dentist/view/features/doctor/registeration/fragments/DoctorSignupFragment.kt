package doctor.task.dentist.view.features.doctor.registeration.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import doctor.task.dentist.base.extensions.getSharedPreferences
import doctor.task.dentist.base.extensions.openActivtyFromParent
import doctor.task.dentist.base.extensions.set
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.registration.IRegistration
import doctor.task.dentist.view.features.doctor.adapters.ClinicAdapter
import doctor.task.dentist.view.features.doctor.adapters.SpecialtyAdapter
import kotlinx.android.synthetic.main.fragment_doctor_registration.*
import kotlinx.android.synthetic.main.fragment_doctor_signup.*

class DoctorSignupFragment : Fragment(),
    INavigation,
    IRegistration {

    var parentViewPager: ViewPager? = null
    val clinicAdapter =
        ClinicAdapter()
    val specialtyAdapter =
        SpecialtyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        //TODO:: Set specialty and clinic data
    }

    private fun initView() {

        //Parent view pager
        parentViewPager = activity?.supportFragmentManager?.fragments?.get(0)?.doctor_registration_viewpager

        doctor_signup_specialty_recyclerview.layoutManager = LinearLayoutManager(context)
        doctor_signup_clinic_recyclerview.layoutManager = LinearLayoutManager(context)

        //Set adapters
        doctor_signup_clinic_recyclerview.adapter = clinicAdapter
        doctor_signup_specialty_recyclerview.adapter = specialtyAdapter
        setSpecialtyClinicData()

        doctor_signup_login_layout.setOnClickListener {
            fragmentSwitch(0)
        }
        doctor_signup_btn.setOnClickListener {
            //Set user to doctor
            setUserType()
            openActivtyFromParent(ReservationListActivity::class.java)
            activity?.finishAffinity()
        }
        doctor_signup_add_appointment_layout.setOnClickListener {
            //TODO:: add appointment to appointments list
        }
    }

    private fun setSpecialtyClinicData() {
        //Specialty data
        val specialtyList = arrayListOf<String>()
        specialtyList.add("خلع أسنان")
        specialtyList.add("تقويم")
        specialtyList.add("زراعة أسنان")
        specialtyList.add("تنظيف")
        //Update adapter
        specialtyAdapter.setData(specialtyList)

        //Clinic data
        val clinicList = arrayListOf<String>()
        clinicList.add("عيادة 1")
        clinicList.add("عيادة 2")
        clinicList.add("عيادة 3")
        clinicList.add("عيادة 4")
        //Update adapter
        clinicAdapter.setData(clinicList)
    }

    //Switch to doctor login fragment
    override fun fragmentSwitch(id: Int) {
        parentViewPager?.setCurrentItem(id)
    }

    override fun setUserType() {
        getSharedPreferences().set("user","Doctor")
    }

}
