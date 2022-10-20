package lab2;

import lab2.data.ResourceDataSearcher;
import lab2.data.SubscriptionManager;
import lab2.model.ActionStatus;
import lab2.model.CourseInstance;
import lab2.model.Instructor;
import lab2.model.Student;
import lab2.service.RAMCourseInstructorService;
import lab2.service.RAMStudentService;
import lab2.data.RAMResourceData;

public class Main {
    public static void main(String[] args){
        RAMResourceData resourceData = new RAMResourceData();
        ResourceDataSearcher resourceDataSearcher = new ResourceDataSearcher(resourceData);
        SubscriptionManager subscriptionManager = new SubscriptionManager(resourceDataSearcher);
        RAMStudentService studentService = new RAMStudentService(subscriptionManager);
        RAMCourseInstructorService courseInstructorService = new RAMCourseInstructorService(subscriptionManager);

        ActionStatus result = studentService.subscribe(102, 100002);
        System.out.println(result.toString()); // OK

        result = studentService.subscribe(102, 100001);
        System.out.println(result.toString()); // NOK

        result = studentService.subscribe(102, 100003);
        System.out.println(result.toString()); // NOK

        CourseInstance[] instancesTest = studentService.findAllSubscriptionsByStudentId(102); // one subscription

        result = studentService.unsubscribe(102, 100001);
        System.out.println(result.toString()); // NOK

        result = studentService.unsubscribe(102, 100002);
        System.out.println(result.toString()); // OK

        instancesTest = studentService.findAllSubscriptionsByStudentId(102); // no subscriptions

        // resubscribe
        result = studentService.subscribe(102, 100002);
        System.out.println(result.toString()); // OK

        Student[] studentsTest = courseInstructorService.findStudentsByCourseId(100002); // student 102
        studentsTest = courseInstructorService.findStudentsByCourseId(100001); // no students

        studentsTest = courseInstructorService.findStudentsByInstructorId(9002); // student 102

        Instructor[] replacementTest = courseInstructorService.findReplacement(9002, 100002); // 9001, 9004
        replacementTest = courseInstructorService.findReplacement(9003, 100003); // none
    }
}
