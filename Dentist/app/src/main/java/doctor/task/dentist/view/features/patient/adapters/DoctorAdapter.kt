package doctor.task.dentist.view.features.patient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import doctor.task.dentist.R
import doctor.task.dentist.models.doctor.Doctor
import doctor.task.dentist.models.doctor.WorkingTime
import doctor.task.dentist.view.features.patient.IDoctor

class DoctorAdapter(private val callback: IDoctor):
    RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    var doctorsList = arrayListOf<Doctor>()

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
        return doctorsList.size
    }

    fun setData(newData: ArrayList<Doctor>){
        doctorsList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doctor_name.text = doctorsList[position].fullName

        holder.doctor_dates.text = getDoctorDates(doctorsList[position].workingTime)
        val imageUrl = doctorsList[position].imageUrl
        Picasso.with(callback.getContext()).load(imageUrl).into(holder.doctor_image)
    }

    private fun getDoctorDates(workingTime: List<WorkingTime>): CharSequence? {
        var workingTimes = ""
        if (workingTime != null) {
            if (workingTime.size > 0) {
                for (time in workingTime) {
                    workingTimes = "${time.day} From ${time.from} To ${time.to} \n"
                }
            }else{
                workingTimes = "No working times"
            }
        }else{
            workingTimes = "Not set"
        }
        return workingTimes
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var doctor_image = itemView.findViewById<CircleImageView>(R.id.doctor_image)
        var doctor_name = itemView.findViewById<TextView>(R.id.doctor_name)
        var doctor_dates = itemView.findViewById<TextView>(R.id.doctor_dates)
    }
}