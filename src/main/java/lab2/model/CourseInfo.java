package lab2.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Класс для базовой информации о курсе
 */
public class CourseInfo {

    /**
     * идентификатор курса
     */
    private long id;

    /**
     * название курса
     */
    private String name;

    /**
     * краткое описание курса
     */
    private String description;

    /**
     * Список идентификаторов курсов, которые нужно обязательно пройти до начала данного курса
     */
    private long[] prerequisites;

    /**
     * список категорий студентов, которые могут посещать курс
     */
    private StudentCategory[] studentCategories;

    public long getId() {
        return id;
    }

    public long[] getPrerequisites() {
        return prerequisites;
    }

    public StudentCategory[] getStudentCategories() {
        return studentCategories;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrerequisites(long[] prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setStudentCategories(StudentCategory[] studentCategories) {
        this.studentCategories = studentCategories;
    }
}
