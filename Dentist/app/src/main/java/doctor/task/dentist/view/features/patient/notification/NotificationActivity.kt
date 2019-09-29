package doctor.task.dentist.view.features.patient.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import doctor.task.dentist.R
import doctor.task.dentist.models.Notification
import doctor.task.dentist.view.features.patient.adapters.NotificationAdapter
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    val adapter = NotificationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //TODO:: load notifications
        setDummyData()
        initView()
    }

    private fun initView() {
        notification_recyclerview.layoutManager = LinearLayoutManager(this)
        notification_recyclerview.adapter = adapter

        back.setOnClickListener { onBackPressed() }
    }

    private fun setDummyData() {
        val notification_list = arrayListOf<Notification>()
        notification_list.add(Notification())
        notification_list.add(Notification())

        adapter.setData(notification_list)
    }
}
