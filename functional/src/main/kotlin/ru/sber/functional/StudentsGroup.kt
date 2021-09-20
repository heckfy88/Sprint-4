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
