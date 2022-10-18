package lab2.model;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    int[] canTeach;

    public int[] getTeachableCourseIds() {
        return canTeach;
    }

    public void setCanTeach(int[] canTeach) {
        this.canTeach = canTeach;
    }
}
