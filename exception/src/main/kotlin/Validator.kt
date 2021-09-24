abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val validationResultList: MutableList<ErrorCode> = mutableListOf()

        val phoneRegex: Regex = "[78][0-9]+$".toRegex()

        if (value != null) {
            if (value.isEmpty()) {
                return listOf(ErrorCode.EMPTY_VALUE)
            }
        } else {
            return listOf(ErrorCode.NULL_VALUE)
        }

        if (value.length != 11) {
            validationResultList.add(ErrorCode.INVALID_PHONE_LENGTH)
        }

        if (!value.matches(phoneRegex)) {
            validationResultList.add(ErrorCode.INVALID_PHONE_FORMAT)
        }

        return validationResultList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val validationResultList: MutableList<ErrorCode> = mutableListOf()

        val nameRegex: Regex = "[А-Я][а-я]*".toRegex()

        if (value != null) {
            if (value.isEmpty()) {
                return listOf(ErrorCode.EMPTY_VALUE)
            }
        } else {
            return listOf(ErrorCode.NULL_VALUE)
        }

        if (value.length > 16) {
            validationResultList.add(ErrorCode.INVALID_NAME_LENGTH)
        }

        if (!value.matches(nameRegex)) {
            validationResultList.add(ErrorCode.INVALID_NAME_FORMAT)
        }

        return validationResultList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val validationResultList: MutableList<ErrorCode> = mutableListOf()

        val emailRegex: Regex = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()

        if (value != null) {
            if (value.isEmpty()) {
                return listOf(ErrorCode.EMPTY_VALUE)
            }
        } else {
            return listOf(ErrorCode.NULL_VALUE)
        }

        if (value.length >= 32) {
            validationResultList.add(ErrorCode.INVALID_EMAIL_LENGTH)
        }

        if (!value.matches(emailRegex)) {
            validationResultList.add(ErrorCode.INVALID_EMAIL_FORMAT)
        }

        return validationResultList
    }
}

class SnilsValidator : Validator<String>() {

    private fun validateControlNum(snilsNumberString: String): Boolean {

        var controlNumber: Int = -1

        val snilsNumberStringFirstNine: String = snilsNumberString.substring(0,9)
        val snilsNumberStringLastTwo: String = snilsNumberString.takeLast(2)

        val nums: IntArray = IntArray(snilsNumberStringFirstNine.length)


        snilsNumberStringFirstNine.indices.forEach {
            nums[it] = snilsNumberStringFirstNine[it].digitToInt() * (snilsNumberString.length - it - 2)}

        if (nums.sum() < 100) {
            controlNumber = nums.sum()
        }
        if (nums.sum() == 100) {
            controlNumber = 0
        }
        if (nums.sum() > 100) {
            controlNumber = if (nums.sum() % 101 == 100) {
                0
            } else {
                nums.sum() % 101
            }
        }

        return (controlNumber.toString() == snilsNumberStringLastTwo)
    }

    override fun validate(value: String?): List<ErrorCode> {

        val validationResultList: MutableList<ErrorCode> = mutableListOf()

        val snilsRegex: Regex = "[0-9]+".toRegex()

        if (value != null) {
            if (value.isEmpty()) {
                return listOf(ErrorCode.EMPTY_VALUE)
            }
        } else {
            return listOf(ErrorCode.NULL_VALUE)
        }

        if (value.length != 11) {
            validationResultList.add(ErrorCode.INVALID_SNILS_LENGTH)
        }

        if (!value.matches(snilsRegex)) {
            validationResultList.add(ErrorCode.INVALID_SNILS_FORMAT)
        }

        if (!validateControlNum(value)) {
            validationResultList.add(ErrorCode.INVALID_SNILS_CONTROL_SUM)
        }



        return validationResultList
    }

}

