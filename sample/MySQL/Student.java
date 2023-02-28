package MySQL;

public class Student {
    private String id;
    private String name;
    private String address;
    private String email;
    private String major;
    private float avgGrade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public Student() {}

    public void setMajor(String major) {
        this.major = major;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Student(String id, String name, String address, String email, String major, float avgGrade) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.major = major;
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", major=" + major
                + ", avgGrade=" + avgGrade + "]";
    }
}
