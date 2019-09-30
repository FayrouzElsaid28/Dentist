package doctor.task.dentist.view.features.patient.clinic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.view.features.patient.IDoctor
import doctor.task.dentist.view.features.patient.PatientHelper
import doctor.task.dentist.view.features.patient.PatientViewModel
import doctor.task.dentist.view.features.patient.adapters.DoctorAdapter
import doctor.task.dentist.view.features.patient.notification.NotificationActivity
import doctor.task.dentist.view.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_specialty.*

class SpecialtyActivity : AppCompatActivity(), IDoctor {

    val adapter = DoctorAdapter(this)

    private val viewModel: PatientViewModel by lazy {
        ViewModelProviders.of(this).get(PatientViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialty)

        getDoctors()
        initView()
    }

    private fun getDoctors() {
        viewModel.getDoctorsBySpecialty(
            PatientHelper.clinic,
            PatientHelper.specify
        ).observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    Log.d("doctors",PatientHelper.doctorsList.toString())
                    adapter.setData(PatientHelper.doctorsList)
                    loading.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    loading.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Resource.Status.EMPTY -> {
                    loading.visibility = View.GONE
                }
            }
        })
    }

    private fun initView() {
        doctors_recyclerview.layoutManager = LinearLayoutManager(this)
        doctors_recyclerview.adapter = adapter
        //TODO:: Set adapter data

        back.setOnClickListener { onBackPressed() }
        notification_img.setOnClickListener {
            openActivity(this,NotificationActivity::class.java)
        }
    }

    override fun getContext(): Context {
        return applicationContext
    }
}
