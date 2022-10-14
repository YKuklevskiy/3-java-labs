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
        if(canSubscribeForCourse(studentId, courseId)) {
            subscriptionManager.addSubscription(studentId, courseId);
            return ActionStatus.OK;
        }
        return ActionStatus.NOK;
    }

    private boolean canSubscribeForCourse(long studentId, long courseId){
        return  courseHasNotBegunYet(courseId) &&
                courseCategoryIsAvailableForStudent(courseId, studentId) &&
                studentHasCompletedAllRequiredCourses(studentId, courseId) &&
                courseHasAvailablePlaces(courseId);
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        if(canUnsubscribeFromCourse(studentId, courseId)) {
            subscriptionManager.removeSubscription(studentId, courseId);
            return ActionStatus.OK;
        }
        return ActionStatus.NOK;
    }

    private boolean canUnsubscribeFromCourse(long studentId, long courseId){
        return  studentSubscribedForCourse(studentId, courseId) &&
                courseHasNotBegunYet(courseId);
    }

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        return subscriptionManager.findAllSubscriptionsByStudentId(studentId);
    }
}
