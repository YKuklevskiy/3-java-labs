package lab2.data;

import lab2.model.*;
import java.util.List;

public interface ResourceData {

    public List<CourseInfo> getCoursesInfo();
    public List<CourseInstance> getCourseInstances();
    public List<Instructor> getInstructors();
    public List<CategorizedStudent> getStudents();

}
