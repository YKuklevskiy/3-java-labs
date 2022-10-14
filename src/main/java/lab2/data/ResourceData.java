package lab2.data;


import lab2.model.*;

public interface ResourceData {

    public CourseInfo[] getCoursesInfo();
    public CourseInstance[] getCourseInstances();
    public Instructor[] getInstructors();
    public CategorizedStudent[] getStudents();

}
