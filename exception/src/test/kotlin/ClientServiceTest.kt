import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_PHONE_FORMAT)
    }

    @Test
    fun `fail save client - name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_FORMAT)
    }

    @Test
    fun `fail save client - mail validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_mail.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_EMAIL_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_EMAIL_FORMAT)
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_SNILS_CONTROL_SUM)
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_NAME_FORMAT)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_PHONE_FORMAT)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_EMAIL_FORMAT)
        assertEquals(exception.errorCode[6], ErrorCode.INVALID_SNILS_CONTROL_SUM)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}