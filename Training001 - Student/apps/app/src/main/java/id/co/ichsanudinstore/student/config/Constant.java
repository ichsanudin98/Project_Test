package id.co.ichsanudinstore.student.config;

import id.co.ichsanudinstore.student.BuildConfig;

public class Constant {
    /*
    * Menyimpan file atau nilai yang credentials
    * */
    public interface URL {
        String BASE = BuildConfig.URL_BASE;
        String COURSE = BuildConfig.URL_COURSE;
        String STUDENT = BuildConfig.URL_STUDENT;
    }
}
