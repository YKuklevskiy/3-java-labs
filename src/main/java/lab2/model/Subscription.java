package lab2.model;

/**
 * Класс для информации о подписке студента на курс
 */
public class Subscription {

    public Subscription(long studentId, long courseInstanceId){
        this.studentId = studentId;
        this.courseInstanceId = courseInstanceId;
    }

    private long studentId;

    private long courseInstanceId;

    public long getStudentId() {
        return studentId;
    }

    public long getCourseInstanceId() {
        return courseInstanceId;
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
