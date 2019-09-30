package doctor.task.dentist.base.extensions

import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import doctor.task.dentist.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URI

private const val PREFERENCE_NAME = "doctor.dentist"

fun <T> Fragment.openActivtyFromParent(cls: Class<T>) {
    activity?.openActivity(activity!!, cls)
}

fun Fragment.makeLongToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.getSharedPreferences(): SharedPreferences =
    activity?.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)!!

fun Fragment.dispachGalleryIntent(){
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
}

fun Fragment.selectTime(time_tv: TextView){
    val timePickerDialog = TimePickerDialog(context,
        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
            time_tv.text=String.format("%02d:%02d", hourOfDay, minutes)
        }, 0, 0, true
    )

    timePickerDialog.show()
}

fun Fragment.getSpecialty(specialtyName: String): String {
    var specialty = ""
    when(specialtyName){
        "Extraction" -> specialty = "tooth-removal"
        "Orthodontic" -> specialty = "orthodontics"
        "Planting" -> specialty = "dental-implants"
        "Cleaning" -> specialty = "cleaning"
        else -> specialty = specialtyName
    }

    return specialty
}

fun Fragment.getClinic(clinicName: String): String{
    var clinic = ""
    when(clinicName){
        "Clinic 1" -> clinic = "clinic1"
        "Clinic 2" -> clinic = "clinic2"
        "Clinic 3" -> clinic = "clinic3"
        "Clinic 4" -> clinic = "clinic4"
        else -> clinic = clinicName
    }

    return clinic
}

fun Fragment.getDay(dayName: String): String {
    var day = ""
    when(dayName){
        "Saturday" -> day = "sat"
        "Sunday" -> day = "sun"
        "Monday" -> day = "mon"
        "Tuesday" -> day = "tues"
        "Wednesday" -> day = "wed"
        "Thursday" -> day = "thurs"
        "Friday" -> day = "fri"
        else -> day = dayName
    }

    return day
}