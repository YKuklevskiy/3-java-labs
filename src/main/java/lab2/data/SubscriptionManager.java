package lab2.data;

import lab2.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SubscriptionManager {
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final ResourceDataSearcher dataSearcher;
    private final ResourceDataSelector dataSelector;

    public SubscriptionManager() {
        this(new RAMResourceData());
    }

    public SubscriptionManager(ResourceData resourceData) {
        this.dataSearcher = new ResourceDataSearcher(resourceData);
        this.dataSelector = new ResourceDataSelector(dataSearcher);
    }

    public void addSubscription(long studentId, long courseId) {
        subscriptions.add(new Subscription(studentId, courseId));
    }

    public void removeSubscription(long studentId, long courseId) {
        int subscriptionIndex = findSubscriptionIndex(studentId, courseId);
        subscriptions.remove(subscriptionIndex);
    }

    private int findSubscriptionIndex(long studentId, long courseId) {
        Subscription searchedSubscription = new Subscription(studentId, courseId);

        return IntStream.range(0, subscriptions.size())
                .filter(i -> searchedSubscription.equals(subscriptions.get(i)))
                .findFirst()
                .getAsInt();
    }

    public boolean canSubscribeForCourseByInstance(long studentId, long courseId){
        CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseId);
        CourseInfo courseInfo = dataSearcher.getCourseInfoById(courseInstance.getCourseId());
        CategorizedStudent student = dataSearcher.getStudentById(studentId);
        if(courseInstance == null || courseInfo == null || student == null) {
            return false;
        }

        return  courseCategoryIsAvailableForStudent(courseInfo, student) &&
                !studentSubscribedForCourseInstance(student, courseInstance) &&
                studentHasCompletedAllRequiredCourses(student, courseInfo) &&
                courseInstanceHasAvailablePlaces(courseInstance) &&
                courseInstanceHasNotBegunYet(courseInstance);
    }

    private boolean courseCategoryIsAvailableForStudent(CourseInfo course, CategorizedStudent student){
        StudentCategory[] availableStudentCategories = course.getStudentCategories();
        return Arrays.stream(availableStudentCategories)
                .anyMatch(category -> category == student.getCategory());
    }

    private boolean studentSubscribedForCourseInstance(CategorizedStudent student, CourseInstance courseInstance) {
        CourseInstance[] studentSubscriptions = findAllInstanceSubscriptionsByStudentId(student.getId());

        return Arrays.stream(studentSubscriptions)
               .anyMatch(subscription -> subscription == courseInstance);
    }

    private boolean studentHasCompletedAllRequiredCourses(CategorizedStudent student, CourseInfo course){
        return LongArrayContainsAllElementsFromOther(student.getCompletedCourses(), course.getPrerequisites());
    }

    private boolean LongArrayContainsAllElementsFromOther(long[] outer, long[] inner) {
        if(inner == null || inner.length == 0){
            return true;
        }
        if(outer == null){
            return false;
        }
        List<Long> outerList = LongStream.of(outer).boxed().toList();
        List<Long> innerList = LongStream.of(inner).boxed().toList();
        return outerList.containsAll(innerList);
    }

    private boolean courseInstanceHasAvailablePlaces(CourseInstance courseInstance){
        if(courseInstance.getCapacity() == 0) {
            return true; // Capacity not set => no limit on attenders
        }
        List<Subscription> currentCourseSubscriptions = findAllSubscriptionsByCourseInstanceId(courseInstance.getId());
        return currentCourseSubscriptions.size() < courseInstance.getCapacity();
    }

    private boolean courseInstanceHasNotBegunYet(CourseInstance courseInstance){
        return LocalDate.now().isBefore(courseInstance.getStartDate());
    }

    public boolean canUnsubscribeFromCourse(long studentId, long courseId){
        CourseInstance courseInstance = dataSearcher.getCourseInstanceById(courseId);
        CategorizedStudent student = dataSearcher.getStudentById(studentId);
        if(courseInstance == null || student == null) {
            return false;
        }

        return  courseInstanceHasNotBegunYet(courseInstance) &&
                studentSubscribedForCourseInstance(student, courseInstance);
    }

    public CourseInstance[] findAllInstanceSubscriptionsByStudentId(long studentId) {
        ArrayList<Long> courseInstanceIds = findAllSubscribedCourseIdsByStudentId(studentId);
        return dataSelector.getCourseInstanceArrayFromIdArray(courseInstanceIds);
    }

    private ArrayList<Long> findAllSubscribedCourseIdsByStudentId(long studentId){
        ArrayList<Long> subscribedCourseInstancesId = new ArrayList<>();
        subscriptions.stream()
                .filter(subscription -> subscription.getStudentId() == studentId)
                .map(subscription -> subscription.getCourseInstanceId())
                .forEach(subscribedCourseInstancesId::add);

        return subscribedCourseInstancesId;
    }

    public Student[] findAllStudentsByCourseInstanceId(long courseInstanceId){
        ArrayList<Subscription> subscriptions = findAllSubscriptionsByCourseInstanceId(courseInstanceId);
        return dataSelector.getStudentArrayFromSubscriptionList(subscriptions);
    }

    private ArrayList<Subscription> findAllSubscriptionsByCourseInstanceId(long courseInstanceId){
        ArrayList<Subscription> resultingSubscriptions = new ArrayList<>();
        subscriptions.stream()
                .filter(subscription -> subscription.getCourseInstanceId() == courseInstanceId)
                .forEach(resultingSubscriptions::add);
        return resultingSubscriptions;
    }

    public Student[] findAllStudentsByInstructorId(long instructorId) {
        ArrayList<Subscription> subscriptions = findAllSubscriptionsByInstructorId(instructorId);
        return dataSelector.getStudentArrayFromSubscriptionList(subscriptions);
    }

    private ArrayList<Subscription> findAllSubscriptionsByInstructorId(long instructorId){
        ArrayList<Subscription> resultingSubscriptions = new ArrayList<>();
        subscriptions.stream()
                .filter(subscription ->
                        dataSelector.isInstructorOfCourseByInstanceId(instructorId, subscription.getCourseInstanceId())
                )
                .forEach(resultingSubscriptions::add);
        return resultingSubscriptions;
    }

    public Instructor[] findReplacementForInstructorByInstanceId(long instructorId, long courseInstanceId) {
        ArrayList<Instructor> availableInstructors = dataSelector.getAllInstructorsForCourseByInstanceId(courseInstanceId);
        int currentInstructorIndex = IntStream.range(0, availableInstructors.size())
                .filter(i -> instructorId == availableInstructors.get(i).getId())
                .findFirst()
                .orElse(-1); // this should only happen if the course is taught by a fraud who can't teach it.

        if(currentInstructorIndex != -1) {
            availableInstructors.remove(currentInstructorIndex);
        }

        return availableInstructors.toArray(new Instructor[0]);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}
