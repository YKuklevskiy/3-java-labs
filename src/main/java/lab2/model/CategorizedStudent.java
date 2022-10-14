package lab2.model;

public class CategorizedStudent extends Student {
    private StudentCategory category;


    public StudentCategory getCategory() {
        return category;
    }

    public void setCategory(StudentCategory category) {
        this.category = category;
    }
}
