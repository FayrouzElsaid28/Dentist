package doctor.task.dentist.models.doctor

data class Doctor(
    val fullName: String,
    val id: String,
    val imageUrl: String,
    val workingTime: List<WorkingTime>
)