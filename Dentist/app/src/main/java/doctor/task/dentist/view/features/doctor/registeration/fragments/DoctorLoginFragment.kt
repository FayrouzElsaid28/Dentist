package doctor.task.dentist.view.features.doctor.registeration.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import doctor.task.dentist.base.extensions.getSharedPreferences
import doctor.task.dentist.base.extensions.makeLongToast
import doctor.task.dentist.base.extensions.openActivtyFromParent
import doctor.task.dentist.base.extensions.set
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.registration.IRegistration
import doctor.task.dentist.view.registration.RegistrationRepository
import doctor.task.dentist.view.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_doctor_login.*
import kotlinx.android.synthetic.main.fragment_doctor_registration.*

class DoctorLoginFragment : Fragment(),
    INavigation,
    IRegistration{

    var parentViewPager: ViewPager? = null

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
    }

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
           login()
        }
    }

    private fun login(){
        if (checkFields()){
            viewModel.login(
                doctor_login_number_et.text.toString(),
                doctor_login_password_et.text.toString()
            ).observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        //Set user to doctor
                        setUserType()

                        //Set token
                        getSharedPreferences().set("token", it.data)
                        RegistrationRepository.token = getSharedPreferences().getString("token", "null")!!
                        Log.d("token", RegistrationRepository.token)

                        //Open another activity
                        openActivtyFromParent(ReservationListActivity::class.java)
                        activity?.finishAffinity()

                        loading.visibility = View.GONE
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                    }
                }
            })
        }else{
            makeLongToast("Please complete all fields")
        }
    }

    override fun checkFields(): Boolean {
        return doctor_login_number_et.text.toString().isNotEmpty() &&
                doctor_login_password_et.text.toString().isNotEmpty()
    }

    //Switch to doctor sign up fragment
    override fun fragmentSwitch(id: Int) {
        parentViewPager?.setCurrentItem(id)
    }

    override fun setUserType() {
        getSharedPreferences().set("user","Doctor")
    }

}
