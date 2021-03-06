package app.quesada.com.canvasreportesapp.interfaces;

import java.util.List;

import app.quesada.com.canvasreportesapp.models.Alumno;
import app.quesada.com.canvasreportesapp.models.Curso;
import app.quesada.com.canvasreportesapp.models.Enrollment;
//import app.quesada.com.canvasreportesapp.models.Grades;
import app.quesada.com.canvasreportesapp.models.ResponseMessage;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    String API_BASE_URL = "https://canvas-api2019.herokuapp.com";

    @GET("/api/courses/")
    Call<List<Curso>> getCursos();

    @GET("/api/courses/{course_id}/users")
    Call<List<Alumno>> getAlumnos(@Path("course_id") Integer course_id);

    @GET("/api/courses/{course_id}/enrollments")
    Call<List<Enrollment>> getEnrollments(@Path("course_id") Integer course_id);

    //@GET("/api/courses/{course_id}/enrollments")
    //Call<Grades> getGrades(@Path("course_id") Integer course_id);

}

