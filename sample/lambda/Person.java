package lambda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;

import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.swing.text.AbstractDocument.BranchElement;

public class Person {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;

    public Person() {
        birthDate = new Date();
    }

    public Person(String firstName, String lastName, String phoneNumber, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = createDate(birthDate);
    }

    private Date createDate(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        if (birthDate != null && !birthDate.isEmpty()) {
            try {
                return dateFormat.parse(birthDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }

    @Override
    public String toString() { // tu dong duoc goi cung sout
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Person{" + "firstName=" + firstName + '\'' + ", lastName=" + lastName + '\'' + ", phoneNumber=" + '\''
                + ", birthDate=" + dateFormat.format(birthDate) + '\'';
    }

    public static int compareFirstName(Person person, Person other) {
        return person.getFirstName().compareTo(other.getFirstName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
