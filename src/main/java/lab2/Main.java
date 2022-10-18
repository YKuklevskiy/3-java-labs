package lab2;

import lab2.data.ResourceDataSearcher;
import lab2.data.SubscriptionManager;
import lab2.model.ActionStatus;
import lab2.service.RAMStudentService;
import lab2.data.RAMResourceData;

public class Main {
    public static void main(String[] args){
        RAMResourceData resourceData = new RAMResourceData();
        ResourceDataSearcher resourceDataSearcher = new ResourceDataSearcher(resourceData);
        SubscriptionManager subscriptionManager = new SubscriptionManager(resourceDataSearcher);
        RAMStudentService studentService = new RAMStudentService(subscriptionManager);

        ActionStatus result = studentService.subscribe(102, 100002);
        System.out.println(result.toString());
    }
}
