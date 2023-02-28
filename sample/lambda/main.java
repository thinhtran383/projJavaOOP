package lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Operator { double operator(double a, double b); } // lambda chi co the trien khai cho functional interface(co1
                                                            // phuong thuc duy nhat)

public class main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", "Doe", "123456789", "01/01/1990"));
        people.add(new Person("Dane", "Doe", "123456789", "01/01/1990"));
        people.add(new Person("Bull", "Thinh", "012345432", "12/12/1999"));
        people.add(new Person("Vull", "Anh", "012345432", "12/12/1999"));
        people.add(new Person("Lull", "Lan", "012345432", "12/12/1999"));
        people.add(new Person("Mull", "Hoa", "012345432", "12/12/1999"));
        // people.sort((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
        people.sort(Person::compareFirstName); // tham chieu lop
        showPeopleInfo(people);
        // vi du 2
        Operator additon = (a, b) -> a + b;
        // System.out.println(additon.operator(20, 10));
    }

    private static void showPeopleInfo(List<Person> people) {
        // lambda expression co 1 tham so dau vao
        people.forEach(p -> {
            System.out.println(p);
        });
    }
}
