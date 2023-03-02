package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String id;
    private String fullName;
    private String major;

    public Student(String id, String fullName, String major) throws InvalidStudentIdException {
        this(id);
        this.fullName = fullName;
        this.major = major;
    }

    public Student() {
    }

    public Student(String id) throws InvalidStudentIdException {
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws InvalidStudentIdException {
        var regex = "^(A|B|C){1}\\d{2}[A-Z]{4}\\d{3}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(id);
        if (matcher.matches()) {
            this.id = id;
        } else {
            this.id = null;
            var msg = "Ma sinh vien khong hop le, vui long kiem tra lai";

        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
