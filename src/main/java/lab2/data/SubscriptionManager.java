package lab2.data;

import lab2.model.CourseInstance;
import lab2.model.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SubscriptionManager {
    private List<Subscription> subscriptions = new ArrayList<>();

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

    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
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
        // get from database by courseInstanceId
    }

}
