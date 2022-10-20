package lab2.service;

import lab2.data.SubscriptionManager;
import lab2.model.Instructor;
import lab2.model.Student;

public class RAMCourseInstructorService implements CourseInstructorService{

    private final SubscriptionManager subscriptionManager;

    public RAMCourseInstructorService(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        return subscriptionManager.findAllStudentsByCourseInstanceId(courseId);
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        return subscriptionManager.findAllStudentsByInstructorId(instructorId);
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        return subscriptionManager.findReplacementForInstructorByInstanceId(instructorId, courseId);
    }
}
