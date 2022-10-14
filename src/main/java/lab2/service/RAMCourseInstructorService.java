package lab2.service;

import lab2.model.Instructor;
import lab2.model.Student;

public class RAMCourseInstructorService implements CourseInstructorService{

    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        return new Student[0];
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        return new Student[0];
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        return new Instructor[0];
    }
}
