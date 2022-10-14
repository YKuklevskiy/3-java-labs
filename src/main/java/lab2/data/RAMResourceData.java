package lab2.data;

import lab2.model.*;
import lab2.reader.*;

import java.io.IOException;

public class RAMResourceData implements ResourceData{
    private CourseInfo[] courseInfoArray;
    private CourseInstance[] courseInstanceArray;
    private Instructor[] instructorArray;
    private CategorizedStudent[] studentArray;

    private CourseDataReader courseDataReader = new CourseDataReader();
    private InstructorDataReader instructorDataReader = new InstructorDataReader();
    private StudentDataReader studentDataReader = new StudentDataReader();

    public RAMResourceData(){
        try {
            courseInfoArray = courseDataReader.readCourseInfoData();
            courseInstanceArray = courseDataReader.readCourseInstanceData();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CourseInfo[] getCoursesInfo() {
        return courseInfoArray;
    }

    @Override
    public CourseInstance[] getCourseInstances() {
        return courseInstanceArray;
    }

    @Override
    public Instructor[] getInstructors() {
        return instructorArray;
    }

    @Override
    public CategorizedStudent[] getStudents() {
        return studentArray;
    }
}
