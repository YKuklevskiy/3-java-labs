package lab2.data;

import lab2.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SubscriptionManager {
    private List<Subscription> subscriptions = new ArrayList<>();
    private ResourceDataSearcher dataSearcher;

    public boolean canSubscribeForCourseByInstance(long studentId, long courseId){
        CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseId);
        CourseInfo courseInfo = dataSearcher.getCourseInfoById(courseInstance.getCourseId());
        CategorizedStudent student = dataSearcher.getStudentById(studentId);

        return  courseCategoryIsAvailableForStudent(courseInfo, student) &&
                studentHasCompletedAllRequiredCourses(student, courseInfo) &&
                courseInstanceHasAvailablePlaces(courseInstance) &&
                courseInstanceHasNotBegunYet(courseInstance);
    }

    private boolean courseCategoryIsAvailableForStudent(CourseInfo course, CategorizedStudent student){
        StudentCategory[] availableStudentCategories = course.getStudentCategories();
        for (StudentCategory category : availableStudentCategories) {
            if (category == student.getCategory()) {
                return true;
            }
        }
        return false;
    }

    private boolean studentHasCompletedAllRequiredCourses(CategorizedStudent student, CourseInfo course){
        return LongArrayContainsAllElementsFromOther(student.getCompletedCourses(), course.getPrerequisites());
    }

    private boolean LongArrayContainsAllElementsFromOther(long[] outer, long[] inner) {
        return Arrays.asList(outer).containsAll(Arrays.asList(inner));
    }

    private boolean courseInstanceHasAvailablePlaces(CourseInstance courseInstance){
        List<Subscription> currentCourseSubscriptions = findAllSubscriptionsByCourseInstanceId(courseInstance.getId());
        return currentCourseSubscriptions.size() < courseInstance.getCapacity();
    }

    private boolean courseInstanceHasNotBegunYet(CourseInstance courseInstance){
        return LocalDate.now().isBefore(courseInstance.getStartDate());
    }

    public boolean canUnsubscribeFromCourse(long studentId, long courseId){
        CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseId);
        CategorizedStudent student = dataSearcher.getStudentById(studentId);

        return  studentSubscribedForCourseInstance(student, courseInstance) &&
                courseInstanceHasNotBegunYet(courseInstance);
    }

    private boolean studentSubscribedForCourseInstance(CategorizedStudent student, CourseInstance courseInstance) {
        CourseInstance[] studentSubscriptions = findAllInstanceSubscriptionsByStudentId(student.getId());

        return Arrays.stream(studentSubscriptions)
                     .filter(subscription -> subscription == courseInstance)
                     .count() > 0;
    }

    public void addSubscription(long studentId, long courseId) {
        subscriptions.add(new Subscription(studentId, courseId));
    }

    public void removeSubscription(long studentId, long courseId) {
        long subscriptionIndex = findSubscriptionIndex(studentId, courseId);
        subscriptions.remove(subscriptionIndex);
    }

    private int findSubscriptionIndex(long studentId, long courseId) {
        Subscription searchedSubscription = new Subscription(studentId, courseId);

        return IntStream.range(0, subscriptions.size())
                        .filter(i -> searchedSubscription.equals(subscriptions.get(i)))
                        .findFirst()
                        .getAsInt();
    }

    public CourseInstance[] findAllInstanceSubscriptionsByStudentId(long studentId) {
        ArrayList<Long> courseInstanceIds = findAllSubscribedCoursesByStudentId(studentId);
        return getCourseInstanceArrayFromIdArray(courseInstanceIds);
    }

    private ArrayList<Long> findAllSubscribedCoursesByStudentId(long studentId){
        ArrayList<Long> subscribedCourseInstancesId = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            if (subscription.getStudentId() == studentId){
                subscribedCourseInstancesId.add(subscription.getCourseInstanceId());
            }
        }

        return subscribedCourseInstancesId;
    }

    private CourseInstance[] getCourseInstanceArrayFromIdArray(ArrayList<Long> courseInstanceIds){
        ArrayList<CourseInstance> searchedCourseInstances = new ArrayList<>();

        for (Long courseInstanceId : courseInstanceIds) {
            CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseInstanceId);
            searchedCourseInstances.add(courseInstance);
        }

        CourseInstance[] courseInstanceArray = new CourseInstance[searchedCourseInstances.size()];
        courseInstanceArray = searchedCourseInstances.toArray(courseInstanceArray);
        return courseInstanceArray;
    }

    public ArrayList<Subscription> findAllSubscriptionsByCourseInstanceId(long courseInstanceId){

    }

    public ArrayList<Subscription> findAllSubscriptionsByInstructorId(long instructorId){

    }

    public Student getStudentInstanceById(long id){
        return dataSearcher.getStudentById(id);
    }

}
