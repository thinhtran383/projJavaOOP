package generic;

public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private float avgGrade;

    public Student(String studentId, String fullName) {
        this.studentId = studentId;
        this.fullName = fullName;
    }

    public Student() {}

    public String toString() {
        return "Student{" + "studentId='" + studentId + '\'' + ", fullName='" + fullName + '\'' + ", email='" + email
                + '\'' + ", avgGrade=" + avgGrade + '}';
    }
}
