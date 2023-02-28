package generic;

import java.util.ArrayList;

public class GenericExample {
    public static void main(String[] args) {
        // dung kieu generic ArrayList<T> de luu cac gia tri kieu String
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Thinh");
        friends.add("Huy");
        friends.add("Hoa");
        friends.add("Hieu");
        // dung kieu generic ArrayList<T> de luu cac gia tri kieu double
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(1.2);
        doubles.add(2.3);
        doubles.add(3.4);
        doubles.add(4.5);
        // dung kieu generic ArrayList<T> de luu cac gia tri kieu Student
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Nguyen Van A"));
        students.add(new Student("B00", "Thinh Tran"));
        students.add(new Student("C00", "Huy Tran"));
        students.add(new Student("D00", "Hoa Tran"));
        printList(friends);
        printList(doubles);
        printList(students);
    }

    public static <T> void printList(ArrayList<T> list) {
        System.out.println("==================================");
        for (var o : list) {
            System.out.println(o);
        }
    }
}
