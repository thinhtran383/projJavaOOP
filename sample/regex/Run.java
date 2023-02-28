package regex;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("Nhap ma sinh vien theo dinh dang da cho");
        var input = new Scanner(System.in);
        var id = input.nextLine();
        try {
            student.setId(id);
        } catch (InvalidStudentIdException e) {
            System.out.println(e.getMessage());
            System.out.println("vi du ve mot ma hop le: B25DCCN123");
        }
        System.out.println("Ma sinh vien la: " + student.getId());
    }
}
