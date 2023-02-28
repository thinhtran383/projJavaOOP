package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Thinh@123";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/student_management";

    public static void main(String[] args) {
        // add student random information
        Student student = new Student();
        student.setId("S000123");
        student.setName("Nguyen Van A");
        student.setAddress("Ha Noi");
        student.setEmail("123@456.com");
        student.setMajor("IT");
        student.setAvgGrade(8.5f);
        try {
            addStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get all students
        try {
            List<Student> students = getStudents();
            printStudents(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // tao helper ket noi database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setAddress(resultSet.getString("address"));
                student.setEmail(resultSet.getString("email"));
                student.setMajor(resultSet.getString("major"));
                student.setAvgGrade(resultSet.getFloat("average_grade"));
                students.add(student);
            }
        }
        return students;
    }

    public static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student(id, name, address, email, major, average_grade) VALUES ('" + student.getId()
                + "', '" + student.getName() + "', '" + student.getAddress() + "', '" + student.getEmail() + "', '"
                + student.getMajor() + "', " + student.getAvgGrade() + ")";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
