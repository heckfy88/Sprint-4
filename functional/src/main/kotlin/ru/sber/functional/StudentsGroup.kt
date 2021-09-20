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
            ),
            Student(
                firstName = "John",
                lastName = "Doea",
                averageRate = 4.0,
            ),
            Student(
                firstName = "Johna1",
                lastName = "Doea232",
                averageRate = 4.0,
            ),
        )
    )

    println(a.filterByPredicate { condition -> condition.firstName == "John" && condition.lastName == "Doe" })

}