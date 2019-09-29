package doctor.task.dentist.models.doctor

data class WorkingTime(
    val _id: String,
    val day: String,
    val from: Int,
    val to: Int
)