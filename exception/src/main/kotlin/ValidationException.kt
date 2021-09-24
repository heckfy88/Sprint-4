class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    NULL_VALUE(101, "Null значение"),
    EMPTY_VALUE(102, "Пустое значение"),


    INVALID_PHONE_LENGTH(103, "Указанный номер телефона неправильной длины"),
    INVALID_NAME_LENGTH(203, "Указанные имя/фамилия более 16 символов"),
    INVALID_EMAIL_LENGTH(303, "Указанное имя почты более 32 символов"),
    INVALID_SNILS_LENGTH(403, "Указанный номер СНИЛСа неправильной длины"),

    INVALID_PHONE_FORMAT(104, "Указанный номер телефона не соответствует формату"),
    INVALID_NAME_FORMAT(204, "Указанные имя/фамилия не соответствует формату"),
    INVALID_EMAIL_FORMAT(304, "Указанное имя почты не соответствует формату"),
    INVALID_SNILS_FORMAT(404, "Указанный номер СНИЛСа не соответствует формату"),

    INVALID_SNILS_CONTROL_SUM(305, "Указанный номер СНИЛСа некорректен")

}