package lab2.model;

public class CategorizedStudent extends Student {
    private StudentCategory category;

    public CategorizedStudent(Student student) {
        this.setId(student.getId());
        this.setName(student.getName());
        this.setCompletedCourses(student.getCompletedCourses());
    }

    public StudentCategory getCategory() {
        return category;
    }

    public void setCategory(StudentCategory category) {
        this.category = category;
    }
}
