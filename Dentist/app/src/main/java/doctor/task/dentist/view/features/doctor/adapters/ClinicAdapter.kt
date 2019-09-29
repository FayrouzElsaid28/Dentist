package doctor.task.dentist.view.features.doctor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import doctor.task.dentist.R
import doctor.task.dentist.view.features.doctor.registeration.DoctorRegistrationHelper

class ClinicAdapter:
    RecyclerView.Adapter<ClinicAdapter.ViewHolder>() {

    var clinic_list = arrayListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_specialty_clinic,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return clinic_list.size
    }

    fun setData(newData: ArrayList<String>){
        clinic_list = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.speciality_name.text = clinic_list[position]

        if (DoctorRegistrationHelper.doctor_clinic == position){
            holder.speciality_circle.setImageResource(R.drawable.speciality_circle_checked)
        }else{
            holder.speciality_circle.setImageResource(R.drawable.speciality_circle_unchecked)
        }

        holder.item_specialty.setOnClickListener {
            DoctorRegistrationHelper.doctor_clinic = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var speciality_circle = itemView.findViewById<ImageView>(R.id.speciality_circle)
        var speciality_name = itemView.findViewById<TextView>(R.id.speciality_name)
        var item_specialty = itemView.findViewById<ConstraintLayout>(R.id.item_specialty)
    }
}