package id.co.ichsanudinstore.student.api;

import id.co.ichsanudinstore.student.api.endpoint.course.cud.CourseRequest;
import id.co.ichsanudinstore.student.api.endpoint.course.cud.CourseResponse;
import id.co.ichsanudinstore.student.api.endpoint.student.StudentRequest;
import id.co.ichsanudinstore.student.api.endpoint.student.StudentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/*
* Metode web service serta url dan parameter yang akan dikirimkan
* */
public interface BelajarRealmParser {
    @POST
    Call<CourseResponse> crudCourse(@Url String url,
                                    @Body CourseRequest request);

    @POST
    Call<StudentResponse> crudStudent(@Url String url,
                                      @Body StudentRequest request);
}