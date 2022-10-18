package lab2.data;

import lab2.model.*;
import lab2.reader.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RAMResourceData implements ResourceData {
    private ArrayList<CourseInfo> courseInfoArray = new ArrayList<>();
    private ArrayList<CourseInstance> courseInstanceArray = new ArrayList<>();
    private ArrayList<Instructor> instructorArray = new ArrayList<>();
    private ArrayList<CategorizedStudent> studentArray = new ArrayList<>();

    private CourseDataReader courseDataReader = new CourseDataReader();
    private InstructorDataReader instructorDataReader = new InstructorDataReader();
    private StudentDataReader studentDataReader = new StudentDataReader();

    public RAMResourceData() {
        tryLoadDataFromResourceFiles();
    }

    private void tryLoadDataFromResourceFiles() {
        try {
            loadCourses();
            loadInstructors();
            loadStudents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCourses() throws IOException {
        courseInfoArray.addAll(Arrays.asList(courseDataReader.readCourseInfoData()));
        courseInstanceArray.addAll(Arrays.asList(courseDataReader.readCourseInstanceData()));
    }

    private void loadInstructors() throws IOException {
        instructorArray.addAll(Arrays.asList(instructorDataReader.readInstructorData()));
    }

    private void loadStudents() throws IOException {
        loadCategorizedStudents(StudentCategory.BACHELOR, studentDataReader.readBachelorStudentData());
        loadCategorizedStudents(StudentCategory.MASTER, studentDataReader.readMasterStudentData());
    }

    private void loadCategorizedStudents(StudentCategory category, Student[] rawStudentArray) {
        for (Student student : rawStudentArray) {
            CategorizedStudent newCategorizedStudent = new CategorizedStudent(student);
            newCategorizedStudent.setCategory(category);
            studentArray.add(newCategorizedStudent);
        }
    }

    @Override
    public List<CourseInfo> getCoursesInfo() {
        return courseInfoArray;
    }

    @Override
    public List<CourseInstance> getCourseInstances() {
        return courseInstanceArray;
    }

    @Override
    public List<Instructor> getInstructors() {
        return instructorArray;
    }

    @Override
    public List<CategorizedStudent> getStudents() {
        return studentArray;
    }
}
