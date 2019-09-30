package doctor.task.dentist.view.features.patient.registration.fragments


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import doctor.task.dentist.base.extensions.getSharedPreferences
import doctor.task.dentist.base.extensions.makeLongToast
import doctor.task.dentist.base.extensions.openActivtyFromParent
import doctor.task.dentist.base.extensions.set
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.view.features.patient.MainActivity
import doctor.task.dentist.view.features.patient.adapters.DiseaseAdapter
import doctor.task.dentist.view.registration.IRegistration
import doctor.task.dentist.view.registration.RegistrationRepository
import doctor.task.dentist.view.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_patient_registration.*
import kotlinx.android.synthetic.main.fragment_patient_signup.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import javax.xml.datatype.DatatypeConstants

class PatientSignupFragment : Fragment(),
    INavigation,
    IRegistration {

    var parentViewPager: ViewPager? = null
    val adapter = DiseaseAdapter()
    val diseaseList = JSONArray()

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        parentViewPager = activity?.supportFragmentManager?.fragments?.get(1)?.pateirnt_registration_viewpager
        patient_signup_history_recyclerview.layoutManager = LinearLayoutManager(context)
        patient_signup_history_recyclerview.adapter = adapter

        patient_signup_login_layout.setOnClickListener {
            fragmentSwitch(0)
        }

        patient_signup_disease_date_et.setOnClickListener {
            getDate(patient_signup_disease_date_et)
        }
        patient_signup_add_disease_layout.setOnClickListener {
            addDisease()
        }

        patient_signup_btn.setOnClickListener {
            Log.d("diseaseList",diseaseList.toString())
            patientSignup()
        }
    }

    private fun patientSignup(){
        if (checkFields()){
            viewModel.registerPatient(
                patient_signup_fullname_et.text.toString(),
                patient_sinup_nnumber_et.text.toString(),
                patient_signup_password_et.text.toString(),
                patient_signup_age_et.text.toString(),
                diseaseList
            ).observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        //Set user to patient
                        setUserType()

                        //Set token
                        getSharedPreferences().set("token", it.data)
                        RegistrationRepository.token = getSharedPreferences().getString("token", "null")!!
                        Log.d("token", RegistrationRepository.token)

                        openActivtyFromParent(MainActivity::class.java)
                        activity?.finishAffinity()

                        loading.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                }
            })
        }else{
            makeLongToast("Please complete all fields")
        }
    }

    override fun checkFields(): Boolean {
        return  patient_signup_fullname_et.text.toString().trim().isNotEmpty() &&
                patient_sinup_nnumber_et.text.toString().trim().isNotEmpty() &&
                patient_signup_password_et.text.toString().isNotEmpty() &&
                patient_signup_age_et.text.toString().isNotEmpty() &&
                diseaseList.length() > 0
    }

    private fun addDisease(){
        if (patient_signup_disease_name_et.text.toString().trim().length < 5)
            makeLongToast("Disease must be at least 5 characters")
        else {
            val diseaseJson = JSONObject()
            diseaseJson.put("disease", patient_signup_disease_name_et.text.toString())
            diseaseJson.put("date", patient_signup_disease_date_et.text.toString())

            diseaseList.put(diseaseJson)
            adapter.setDate(diseaseList)

            //Clear text
            patient_signup_disease_name_et.setText("")
            patient_signup_disease_date_et.setText("")
        }
    }

    @SuppressLint("SetTextI18n")
    fun getDate(textView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            textView.text = """$dayOfMonth-${monthOfYear + 1}-$year"""
        }, year, month, day)
        dpd.show()
    }

    override fun fragmentSwitch(id: Int) {
        parentViewPager?.setCurrentItem(id)
    }

    override fun setUserType() {
        getSharedPreferences().set("user","Patient")
    }

}
