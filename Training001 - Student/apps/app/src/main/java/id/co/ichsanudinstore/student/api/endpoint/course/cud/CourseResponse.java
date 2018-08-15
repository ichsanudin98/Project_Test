package id.co.ichsanudinstore.student.api.endpoint.course.cud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.ichsanudinstore.student.api.endpoint.GeneralResponse;

public class CourseResponse extends GeneralResponse {
    @SerializedName("DT")
    @Expose
    private List<CourseResponseDetail> data;

    @SerializedName("CI")
    @Expose
    private Long[] courses_id;

    public List<CourseResponseDetail> getData() {
        return data;
    }

    public void setData(List<CourseResponseDetail> data) {
        this.data = data;
    }

    public Long[] getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(Long[] courses_id) {
        this.courses_id = courses_id;
    }
}
