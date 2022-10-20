package lab2.data;

import lab2.model.*;
import java.util.List;

public interface ResourceData {

    List<CourseInfo> getCoursesInfo();
    List<CourseInstance> getCourseInstances();
    List<Instructor> getInstructors();
    List<CategorizedStudent> getStudents();

}
