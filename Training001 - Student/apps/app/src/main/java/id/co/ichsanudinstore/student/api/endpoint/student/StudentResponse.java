package id.co.ichsanudinstore.student.api.endpoint.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.ichsanudinstore.belajarrealm.api.endpoint.GeneralResponse;

public class StudentResponse extends GeneralResponse {
    @SerializedName("DT")
    @Expose
    private List<StudentResponseDetail> data;

    @SerializedName("SI")
    @Expose
    private Long student_id;

    public List<StudentResponseDetail> getData() {
        return data;
    }

    public void setData(List<StudentResponseDetail> data) {
        this.data = data;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}
