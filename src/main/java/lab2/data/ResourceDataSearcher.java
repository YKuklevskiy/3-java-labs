package lab2.data;

import lab2.model.*;

import java.util.List;

public class ResourceDataSearcher {
    private ResourceData data;

    public ResourceDataSearcher(ResourceData data){
        this.data = data;
    }

    public List<CourseInfo> getAllCoursesInfo() {
        return data.getCoursesInfo();
    }

    public List<CourseInstance> getAllCourseInstances() {
        return data.getCourseInstances();
    }

    public List<Instructor> getAllInstructors() {
        return data.getInstructors();
    }

    public List<CategorizedStudent> getAllStudents() {
        return data.getStudents();
    }

    public CourseInstance getCourseInstanceById(long id) {
        return  data.getCourseInstances().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public CourseInfo getCourseInfoById(long id) {
        return  data.getCoursesInfo().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public CategorizedStudent getStudentById(long studentId) {
        return  data.getStudents().stream()
                .filter(x -> x.getId() == studentId)
                .findFirst()
                .orElse(null);
    }
}
