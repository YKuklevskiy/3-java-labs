package lab2.service;

import lab2.data.SubscriptionManager;
import lab2.model.CourseInstance;
import lab2.model.Instructor;
import lab2.model.Student;
import lab2.model.Subscription;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class RAMCourseInstructorService implements CourseInstructorService{

    private SubscriptionManager subscriptionManager;

    public RAMCourseInstructorService(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        ArrayList<Subscription> subscriptions = subscriptionManager.findAllSubscriptionsByCourseInstanceId(courseId);
        return getStudentArrayFromSubscriptionArray(subscriptions);
    }

    private Student[] getStudentArrayFromSubscriptionArray(ArrayList<Subscription> subscriptions){
        ArrayList<Student> subscribedStudents = new ArrayList<>();
        for(Subscription subscription : subscriptions){
            long studentId = subscription.getStudentId();
            Student subscribedStudent = subscriptionManager.getStudentInstanceById(studentId);
            subscribedStudents.add(subscribedStudent);
        }

        Student[] subscribedStudentsArray = new Student[subscribedStudents.size()];
        subscribedStudentsArray = subscribedStudents.toArray(subscribedStudentsArray);
        return subscribedStudentsArray;
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        ArrayList<Subscription> subscriptions = subscriptionManager.findAllSubscriptionsByInstructorId(instructorId);
        return getStudentArrayFromSubscriptionArray(subscriptions);
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        CourseInstance courseInstance = subscriptionManager.getCourseInstanceByInstanceId(courseId);
        long courseInfoId = courseInstance.getCourseId();
        ArrayList<Instructor> availableInstructors = subscriptionManager.getAllInstructorsForCourseByInfoId(courseInfoId);
        int currentInstructorIndex = IntStream.range(0, availableInstructors.size())
                                              .filter(i -> instructorId == availableInstructors.get(i).getId())
                                              .findFirst()
                                              .getAsInt();
        availableInstructors.remove(currentInstructorIndex);

        Instructor[] availableInstructorsArray = new Instructor[availableInstructors.size()];
        availableInstructorsArray = availableInstructors.toArray(availableInstructorsArray);
        return availableInstructorsArray;
    }
}
