package doctor.task.dentist.view.features.patient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R
import doctor.task.dentist.view.features.patient.registration.PatientRegistrationHelper
import org.json.JSONArray

class DiseaseAdapter:
    RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {

    var disease_list = JSONArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_disease,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return disease_list.length()
    }

    fun setDate(newData: JSONArray){
        disease_list = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.disease_name.text = disease_list.getJSONObject(position).getString("disease")
        holder.disease_date.text = disease_list.getJSONObject(position).getString("date")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var disease_name = itemView.findViewById<TextView>(R.id.disease_name)
        var disease_date = itemView.findViewById<TextView>(R.id.disease_date)
    }
}