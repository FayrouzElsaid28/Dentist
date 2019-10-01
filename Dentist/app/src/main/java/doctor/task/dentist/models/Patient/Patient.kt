package doctor.task.dentist.models.Patient

data class Patient(
    val code: Int,
    val error: Any,
    val result: List<Result>
)