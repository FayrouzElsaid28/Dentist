package doctor.task.dentist.view.features.patient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R
import doctor.task.dentist.models.Notification

class NotificationAdapter:
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    var notification_list = arrayListOf<Notification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notification,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return notification_list.size
    }

    fun setData(newData: ArrayList<Notification>){
        notification_list = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notification_title.text = notification_list[position].notification_title
        holder.notification_details.text = notification_list[position].notification_details
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notification_title = itemView.findViewById<TextView>(R.id.notification_title)
        var notification_details = itemView.findViewById<TextView>(R.id.notification_details)
    }
}