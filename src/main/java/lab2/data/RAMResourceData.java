package lab2.data;

import lab2.model.*;
import lab2.reader.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RAMResourceData implements ResourceData {
    private final ArrayList<CourseInfo> courseInfoArray = new ArrayList<>();
    private final ArrayList<CourseInstance> courseInstanceArray = new ArrayList<>();
    private final ArrayList<Instructor> instructorArray = new ArrayList<>();
    private final ArrayList<CategorizedStudent> studentArray = new ArrayList<>();

    private final CourseDataReader courseDataReader = new CourseDataReader();
    private final InstructorDataReader instructorDataReader = new InstructorDataReader();
    private final StudentDataReader studentDataReader = new StudentDataReader();

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
