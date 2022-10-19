package lab2.model;

import java.time.LocalDate;

/**
 * Класс для информации о подписке студента на курс
 */
public class Subscription {

    public Subscription(long studentId, long courseInstanceId){
        this.studentId = studentId;
        this.courseInstanceId = courseInstanceId;
        this.subscriptionDate = LocalDate.now();
    }

    private long studentId;

    private long courseInstanceId;

    private LocalDate subscriptionDate;

    public long getStudentId() {
        return studentId;
    }

    public long getCourseInstanceId() {
        return courseInstanceId;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Subscription))
            return false;
        Subscription other = (Subscription) obj;
        return this.studentId == other.studentId && this.courseInstanceId == other.courseInstanceId;
    }
}
