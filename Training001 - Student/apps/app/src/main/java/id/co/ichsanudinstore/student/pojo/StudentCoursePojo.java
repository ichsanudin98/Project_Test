package id.co.ichsanudinstore.student.pojo;

/*
* Tempat penyimpanan sementara saat menambahkan course untuk student
* */
public class StudentCoursePojo {
    private Long id;
    private String student_nik;
    private Long course_id;
    private String course_name;

    public StudentCoursePojo() {
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

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
