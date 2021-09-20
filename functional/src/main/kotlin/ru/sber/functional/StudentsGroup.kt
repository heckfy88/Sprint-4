package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun initStudents(students: List<Student>) {
        this.students = students
    }

    fun filterByPredicate(condition: (Student) -> Boolean): List<Student> {
        if (this::students.isInitialized) {
            return students.filter(condition)
        }
        return emptyList()
     }
}

fun main() {
    val a = StudentsGroup()
    a.initStudents(
        listOf(
            Student(
                firstName = "John",
                lastName = "Doe",
                averageRate = 2.3,
                age = 0,
                city = "No city mentioned",
                specialization = "No specialization mentioned",
                prevEducation = "No previous education",
                middleName = "No middle name"
            ),
            Student(
                firstName = "John",
                lastName = "Doea",
                averageRate = 4.0,
                age = 10,
                city = "No city mentioned",
                specialization = "No specialization mentioned",
                prevEducation = "No previous education",
                middleName = "No middle name"
            ),
            Student(
                firstName = "Johna1",
                lastName = "Doea232",
                averageRate = 4.0,
                age = 20,
                city = "No city mentioned",
                specialization = "No specialization mentioned",
                prevEducation = "No previous education",
                middleName = "No middle name"
            ),
        )
    )

    println(a.filterByPredicate { condition -> condition.firstName == "John" && condition.lastName == "Doe" })

}