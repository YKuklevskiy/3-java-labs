package lab2.data;

import lab2.model.CourseInstance;
import lab2.model.Instructor;
import lab2.model.Student;
import lab2.model.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceDataSelector {

    private final ResourceDataSearcher dataSearcher;

    public ResourceDataSelector(ResourceDataSearcher dataSearcher){
        this.dataSearcher = dataSearcher;
    }

    private Student getStudentInstanceById(long id){
        return dataSearcher.getStudentById(id);
    }

    private CourseInstance getCourseInstanceByInstanceId(long id){
        return dataSearcher.getCourseInstanceById(id);
    }

    public CourseInstance[] getCourseInstanceArrayFromIdArray(ArrayList<Long> courseInstanceIds){
        ArrayList<CourseInstance> searchedCourseInstances = new ArrayList<>();

        for (Long courseInstanceId : courseInstanceIds) {
            CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseInstanceId);
            searchedCourseInstances.add(courseInstance);
        }

        return searchedCourseInstances.toArray(new CourseInstance[0]);
    }

    public Student[] getStudentArrayFromSubscriptionList(ArrayList<Subscription> subscriptions){
        ArrayList<Student> subscribedStudents = new ArrayList<>();
        for(Subscription subscription : subscriptions){
            long studentId = subscription.getStudentId();
            Student subscribedStudent = getStudentInstanceById(studentId);
            subscribedStudents.add(subscribedStudent);
        }

        return subscribedStudents.toArray(new Student[0]);
    }

    public boolean isInstructorOfCourseByInstanceId(long instructorId, long courseInstanceId) {
        return dataSearcher.getCourseInstanceById(courseInstanceId).getInstructorId() == instructorId;
    }

    public ArrayList<Instructor> getAllInstructorsForCourseByInstanceId(long courseInstanceId) {
        CourseInstance courseInstance = getCourseInstanceByInstanceId(courseInstanceId);
        long courseInfoId = courseInstance.getCourseId();

        List<Instructor> instructors = dataSearcher.getAllInstructors();
        ArrayList<Instructor> resultingInstructors = new ArrayList<>();
        instructors.stream()
                .filter(instructor -> isCourseTeachableForInstructor(courseInfoId, instructor))
                .forEach(resultingInstructors::add);

        return resultingInstructors;
    }

    private boolean isCourseTeachableForInstructor(long courseInfoId, Instructor instructor) {
        return Arrays.stream(instructor.getTeachableCourseIds())
                .anyMatch(teachableCourseId -> teachableCourseId == courseInfoId);
    }

}
