package com.Models;

public class Notification {
    private String id;
    private String studentid;
    private String created_at;

    public Notification(String id, String studentid, String created_at) {
        this.id = id;
        this.studentid = studentid;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
