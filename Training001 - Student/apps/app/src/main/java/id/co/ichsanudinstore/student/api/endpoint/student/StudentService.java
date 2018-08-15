package id.co.ichsanudinstore.student.api.endpoint.student;

import android.app.Activity;

import id.co.ichsanudinstore.student.api.BelajarRealmAPI;
import id.co.ichsanudinstore.student.api.BelajarRealmParser;
import retrofit2.Call;

public class StudentService {
    public static Call<StudentResponse> sendRequest(
            Activity activity,
            String url,
            Integer type,
            Long student_id,
            String student_name,
            String student_nik,
            String student_phone,
            String student_major,
            Long[] student_courses
    ) {
        StudentRequest requestParam = new StudentRequest();
        requestParam.setType(type);
        requestParam.setStudent_id(student_id);
        requestParam.setStudent_name(student_name);
        requestParam.setStudent_nik(student_nik);
        requestParam.setStudent_phone(student_phone);
        requestParam.setStudent_major(student_major);
        requestParam.setStudent_courses(student_courses);

        return BelajarRealmAPI.getRetrofit(activity)
                .create(BelajarRealmParser.class)
                .crudStudent(url, requestParam);
    }
}
