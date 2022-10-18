package lab2.service;

import lab2.data.SubscriptionManager;
import lab2.model.ActionStatus;
import lab2.model.CourseInstance;

public class RAMStudentService implements StudentService{
    private SubscriptionManager subscriptionManager;

    public RAMStudentService(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        if(subscriptionManager.canSubscribeForCourseByInstance(studentId, courseId)) {
            subscriptionManager.addSubscription(studentId, courseId);
            return ActionStatus.OK;
        }
        return ActionStatus.NOK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        if(subscriptionManager.canUnsubscribeFromCourse(studentId, courseId)) {
            subscriptionManager.removeSubscription(studentId, courseId);
            return ActionStatus.OK;
        }
        return ActionStatus.NOK;
    }

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        return subscriptionManager.findAllInstanceSubscriptionsByStudentId(studentId);
    }
}
