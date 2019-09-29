package doctor.task.dentist.view.features.patient.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R

class DoctorAdapter:
    RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_doctor,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Dates","")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}