package id.co.ichsanudinstore.student.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StudentCourseEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private String student_nik;
    private Long course_id;

    public StudentCourseEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent_nik() {
        return student_nik;
    }

    public void setStudent_nik(String student_nik) {
        this.student_nik = student_nik;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }
}
