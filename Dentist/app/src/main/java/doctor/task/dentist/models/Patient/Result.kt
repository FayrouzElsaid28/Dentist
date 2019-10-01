package doctor.task.dentist.models.Patient

data class Result(
    val PaientId: String,
    val age: Int,
    val date: String,
    val day: String,
    val fullName: String,
    val hour: String,
    val patientHistory: List<PatientHistory>,
    val reservationId: String
)