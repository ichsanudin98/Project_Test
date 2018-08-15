package id.co.ichsanudinstore.student.api.endpoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponse {
    @SerializedName("RC")
    @Expose
    private Integer response_code;

    @SerializedName("RM")
    @Expose
    private String response_message;

    public Integer getResponse_code() {
        return response_code;
    }

    public void setResponse_code(Integer response_code) {
        this.response_code = response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }
}
