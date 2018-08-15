package id.co.ichsanudinstore.student.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StudentEntity extends RealmObject {
    @PrimaryKey
    private String nik;
    private String name;
    private String majors;
    private RealmList<StudentCourseEntity> courses;
    {
        this.courses = new RealmList<>();
    }

    public StudentEntity() {
        super();
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public RealmList<StudentCourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(RealmList<StudentCourseEntity> courses) {
        this.courses = courses;
    }
}
