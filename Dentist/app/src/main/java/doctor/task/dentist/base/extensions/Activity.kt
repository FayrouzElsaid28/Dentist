package doctor.task.dentist.base.extensions

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import doctor.task.dentist.view.registration.RegistrationActivity

private const val PREFERENCE_NAME = "doctor.dentist"

fun <T> Activity.openActivity(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls))
}

fun <T> Activity.openActivityClearStack(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
}

fun Activity.selectTime(time_tv: TextView){
    val timePickerDialog = TimePickerDialog(this,
        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
            val hours = setAccurateHour(hourOfDay)
            if (hours < 9 || hours > 19){
                makeLongToast("Time should be between 9 am and 7 pm")
            }else {
                time_tv.text = String.format("%02d:%02d", hours, minutes)
            }
        }, 0, 0, true
    )

    timePickerDialog.show()
}

fun Activity.logOut(context: Context){
    getSharedPreferences().edit().remove("user").apply()
    openActivity(context,RegistrationActivity::class.java)
}

fun Activity.makeLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.getSharedPreferences(): SharedPreferences =
    getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

fun Activity.getSpecialty(specialtyName: String): String {
    var specialty = ""
    when(specialtyName){
        "Extraction" -> specialty = "tooth-removal"
        "Orthodontic" -> specialty = "orthodontics"
        "Planting" -> specialty = "dental-implants"
        "Cleaning" -> specialty = "cleaning"
    }

    return specialty
}

fun Activity.getClinic(clinicName: String): String{
    var clinic = ""
    when(clinicName){
        "Clinic 1" -> clinic = "clinic1"
        "Clinic 2" -> clinic = "clinic2"
        "Clinic 3" -> clinic = "clinic3"
        "Clinic 4" -> clinic = "clinic4"
    }

    return clinic
}

fun Activity.getDay(dayName: String): String {
    var day = ""
    when(dayName){
        "Saturday" -> day = "sat"
        "Sunday" -> day = "sun"
        "Monday" -> day = "mon"
        "Tuesday" -> day = "tues"
        "Wednesday" -> day = "wed"
        "Thursday" -> day = "thurs"
        "Friday" -> day = "fri"
    }

    return day
}
