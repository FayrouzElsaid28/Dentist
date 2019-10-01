package doctor.task.dentist.view.features.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.base.AdapterToViewCallback
import doctor.task.dentist.base.extensions.logOut
import doctor.task.dentist.base.extensions.makeLongToast
import doctor.task.dentist.base.extensions.openActivity
import doctor.task.dentist.base.helpers.Resource
import doctor.task.dentist.view.features.doctor.adapters.PatientAdapter
import doctor.task.dentist.view.features.doctor.patientFile.PatientFileActivity
import kotlinx.android.synthetic.main.activity_reservation_list.*

class ReservationListActivity : AppCompatActivity(), AdapterToViewCallback {

    val adapter = PatientAdapter(this)

    private val viewModel: DoctorViewModel by lazy {
        ViewModelProviders.of(this).get(DoctorViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        loadPatients()
        initView()
    }

    private fun loadPatients() {
        viewModel.getAllPatients()
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        adapter.setData(DoctorHelper.patientList)
                    }
                    Resource.Status.ERROR -> makeLongToast(it.message!!)
                    Resource.Status.LOADING -> makeLongToast("Loading")
                    Resource.Status.EMPTY -> makeLongToast("No Patients found")
                }
            })
    }

    private fun initView() {
        patients_recyclerview.layoutManager = LinearLayoutManager(this)
        patients_recyclerview.adapter = adapter

        logout.setOnClickListener {
            logOut(this)
            finishAffinity()
        }
    }

    override fun openActivity() {
        openActivity(this,PatientFileActivity::class.java)
    }

}
