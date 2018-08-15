package id.co.ichsanudinstore.student.api.endpoint.course.cud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseResponseDetail {
    @SerializedName("CI")
    @Expose
    private Long course_id;

    @SerializedName("CN")
    @Expose
    private String course_name;

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
