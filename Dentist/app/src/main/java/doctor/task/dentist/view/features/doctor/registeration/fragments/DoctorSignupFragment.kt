package doctor.task.dentist.view.features.doctor.registeration.fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import doctor.task.dentist.R
import doctor.task.dentist.base.INavigation
import doctor.task.dentist.base.extensions.*
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.view.features.doctor.ReservationListActivity
import doctor.task.dentist.view.features.doctor.adapters.AppointmentAdapter
import doctor.task.dentist.view.registration.IRegistration
import doctor.task.dentist.view.features.doctor.adapters.ClinicAdapter
import doctor.task.dentist.view.features.doctor.adapters.SpecialtyAdapter
import doctor.task.dentist.view.features.doctor.registeration.DoctorRegistrationHelper
import doctor.task.dentist.view.registration.RegistrationRepository
import doctor.task.dentist.view.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_doctor_registration.*
import kotlinx.android.synthetic.main.fragment_doctor_signup.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

@Suppress("DEPRECATION")
class DoctorSignupFragment : Fragment(),
    INavigation,
    IRegistration {

    private var parentViewPager: ViewPager? = null
    private val clinicAdapter = ClinicAdapter()
    private val specialtyAdapter = SpecialtyAdapter()
    private var imageFile: File? = null
    private val appointmentAdapter = AppointmentAdapter()
    private val appointmentViewList = JSONArray()
    private val appointmentList = JSONArray()

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
    }

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
    }

    private fun initView() {
        //Parent view pager
        parentViewPager = activity?.supportFragmentManager?.fragments?.get(0)?.doctor_registration_viewpager

        doctor_signup_specialty_recyclerview.layoutManager = LinearLayoutManager(context)
        doctor_signup_clinic_recyclerview.layoutManager = LinearLayoutManager(context)
        doctor_signup_appointments_recyclerview.layoutManager = LinearLayoutManager(context)

        //Set adapters
        doctor_signup_clinic_recyclerview.adapter = clinicAdapter
        doctor_signup_specialty_recyclerview.adapter = specialtyAdapter
        doctor_signup_appointments_recyclerview.adapter = appointmentAdapter
        setSpecialtyClinicData()

        doctor_signup_login_layout.setOnClickListener {
            fragmentSwitch(0)
        }

        //Select image
        doctor_signup_img.setOnClickListener {
            dispachGalleryIntent()
        }

        //Set time
        doctor_signup_appointments_to.setOnClickListener {
            selectTime(doctor_signup_appointments_to)
        }
        doctor_signup_appointments_from.setOnClickListener {
            selectTime(doctor_signup_appointments_from)
        }
        doctor_signup_add_appointment_layout.setOnClickListener {
            setAppointment()
        }

        //Sign up
        doctor_signup_btn.setOnClickListener {
            doctorSignup()
        }
    }

    private fun doctorSignup(){
        if (checkFields()){
            prepareData()
            Log.d("image",imageFile.toString())
            viewModel.registerDoctor(
                doctor_signup_fullname_et.text.toString(),
                imageFile!!,
                doctor_sinup_nnumber_et.text.toString(),
                doctor_signup_password_et.text.toString(),
                doctor_signup_doctor_number_et.text.toString(),
                DoctorRegistrationHelper.doctor_speciality,
                DoctorRegistrationHelper.doctor_clinic,
                doctor_signup_price_et.text.toString(),
                appointmentList
            ).observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        //Set user to doctor
                        setUserType()

                        //Set token
                        getSharedPreferences().set("token", it.data)
                        RegistrationRepository.token = getSharedPreferences().getString("token", "null")!!

                        //Open another activity
                        openActivtyFromParent(ReservationListActivity::class.java)
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
                    Resource.Status.EMPTY -> {
                        loading.visibility = View.GONE
                        Log.d("d","Empty")
                    }
                }
            })
        }else{
            makeLongToast("Please complete all fields")
        }
    }

    private fun prepareData() {
        //Change specialty format
        DoctorRegistrationHelper.doctor_speciality =
            getSpecialty(DoctorRegistrationHelper.doctor_speciality)

        //Change clinic format
        DoctorRegistrationHelper.doctor_clinic =
            getClinic(DoctorRegistrationHelper.doctor_clinic)

        //Change date format
        for (dateIndex in 0 until appointmentViewList.length()){
            val date = appointmentViewList.getJSONObject(dateIndex)

            val hourFrom = date.getString("from").split(':').get(0).toInt()
            val hourTo = date.getString("to").split(':').get(0).toInt()
            val day = getDay(date.getString("day"))

            val jsonObject = JSONObject()
            jsonObject.put("day",day)
            jsonObject.put("from",hourFrom)
            jsonObject.put("to",hourTo)

            appointmentList.put(jsonObject)
        }
    }

    override fun checkFields(): Boolean {
        return doctor_signup_fullname_et.text.toString().trim().isNotEmpty() &&
                doctor_sinup_nnumber_et.text.toString().trim().isNotEmpty() &&
                doctor_signup_password_et.text.toString().trim().isNotEmpty() &&
                doctor_signup_doctor_number_et.text.toString().trim().isNotEmpty() &&
                doctor_signup_price_et.text.toString().trim().isNotEmpty() &&
                imageFile != null &&
                DoctorRegistrationHelper.doctor_speciality.isNotEmpty() &&
                DoctorRegistrationHelper.doctor_clinic.isNotEmpty() &&
                appointmentViewList.length() > 0
    }

    private fun setAppointment(){
        val appointmentJson = JSONObject()
        appointmentJson.put("day", doctor_signup_day_spinner.selectedItem.toString())
        appointmentJson.put("from", doctor_signup_appointments_from.text.toString())
        appointmentJson.put("to", doctor_signup_appointments_to.text.toString())

        appointmentViewList.put(appointmentJson)
        appointmentAdapter.setData(appointmentViewList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == 1 && resultCode == Activity.RESULT_OK
                && null != data
            ) {
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                if (data.data != null) {
                    val mImageUri = data.data

                    // Get the cursor
                    val cursor = activity?.contentResolver?.query(
                        mImageUri!!,
                        filePathColumn, null, null, null
                    )

                    // Move to first row
                    cursor!!.moveToFirst()

                    doctor_signup_img.setImageURI(mImageUri)
                    imageFile = getImageFile(mImageUri!!)
                    cursor.close()
                }

            }
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    //Convert image to file
    private fun getImageFile(image_uri: Uri): File?{
        val selectedImage = MediaStore.Images.Media.getBitmap(
            activity?.contentResolver, image_uri
        )
        if (selectedImage != null) {
            val filesDir = activity?.filesDir
            val imageFile = File(filesDir, android.R.attr.name.toString() + ".jpg")
            val os: OutputStream
            os = FileOutputStream(imageFile)
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()
            return imageFile
        }
        else
            return null
    }

    private fun setSpecialtyClinicData() {
        //Specialty data
        val specialtyList = arrayListOf<String>()
        specialtyList.add("Extraction")
        specialtyList.add("Orthodontic")
        specialtyList.add("Planting")
        specialtyList.add("Cleaning")
        //Update adapter
        specialtyAdapter.setData(specialtyList)

        //Clinic data
        val clinicList = arrayListOf<String>()
        clinicList.add("Clinic 1")
        clinicList.add("Clinic 2")
        clinicList.add("Clinic 3")
        clinicList.add("Clinic 4")
        //Update adapter
        clinicAdapter.setData(clinicList)
    }

    //Switch to doctor login fragment
    override fun fragmentSwitch(id: Int) {
        parentViewPager?.currentItem = id
    }

    override fun setUserType() {
        getSharedPreferences().set("user","Doctor")
    }

}
