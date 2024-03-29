package doctor.task.dentist.view.features.doctor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R
import doctor.task.dentist.view.features.doctor.registeration.DoctorRegistrationHelper
import org.json.JSONArray

class AppointmentAdapter:
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    var appointment_list = JSONArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_appointment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return appointment_list.length()
    }

    fun setData(newData: JSONArray){
        appointment_list = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.appointment_day.text = appointment_list.getJSONObject(position).getString("day")
        holder.appointment_from.text = appointment_list.getJSONObject(position).getString("from")
        holder.appointment_to.text = appointment_list.getJSONObject(position).getString("to")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appointment_day = itemView.findViewById<TextView>(R.id.appointment_day)
        var appointment_from = itemView.findViewById<TextView>(R.id.appointment_from)
        var appointment_to = itemView.findViewById<TextView>(R.id.appointment_to)
    }
}