package abstract_;

import java.util.Date;

public abstract class Person_abstract {
    private String id;
    private String name;
    private String address;
    private Date dateOfBirth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    abstract void earnMoney(); // phuong thuc abstract, chi khai bao khong them gi
}

class Student extends Person_abstract {
    private String sudentId;
    private String major;
    private float avgGrade;

    @Override // phai override lai phuong thuc trong abstract
    public void earnMoney() {
        System.out.println("earn123");
    }
}

class Instructor extends Person_abstract {
    @Override
    public void earnMoney() {
        System.out.println("Teaching");
    }
}
