package id.co.ichsanudinstore.student.api.endpoint.course.cud;

import android.app.Activity;

import id.co.ichsanudinstore.student.api.BelajarRealmAPI;
import id.co.ichsanudinstore.student.api.BelajarRealmParser;
import retrofit2.Call;

public class CourseService {
    public static Call<CourseResponse> sendRequest(
            Activity activity,
            String url,
            Integer type,
            Long course_id,
            String course_name
    ) {
        CourseRequest requestParam = new CourseRequest();
        requestParam.setType(type);
        requestParam.setCourse_id(course_id);
        requestParam.setCourse_name(course_name);

        return BelajarRealmAPI.getRetrofit(activity)
                .create(BelajarRealmParser.class)
                .crudCourse(url, requestParam);
    }
}
