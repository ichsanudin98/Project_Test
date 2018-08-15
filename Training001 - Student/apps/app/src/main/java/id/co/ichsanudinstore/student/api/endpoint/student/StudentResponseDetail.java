package id.co.ichsanudinstore.student.api.endpoint.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentResponseDetail {
    @SerializedName("SI")
    @Expose
    private Long student_id;

    @SerializedName("SN")
    @Expose
    private String student_name;

    @SerializedName("SK")
    @Expose
    private String student_nik;

    @SerializedName("SP")
    @Expose
    private String student_phone;

    @SerializedName("SM")
    @Expose
    private String student_major;

    @SerializedName("CL")
    @Expose
    private Long[] student_courses;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_nik() {
        return student_nik;
    }

    public void setStudent_nik(String student_nik) {
        this.student_nik = student_nik;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getStudent_major() {
        return student_major;
    }

    public void setStudent_major(String student_major) {
        this.student_major = student_major;
    }

    public Long[] getStudent_courses() {
        return student_courses;
    }

    public void setStudent_courses(Long[] student_courses) {
        this.student_courses = student_courses;
    }
}
