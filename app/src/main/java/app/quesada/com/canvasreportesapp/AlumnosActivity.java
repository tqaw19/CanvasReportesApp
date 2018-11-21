package app.quesada.com.canvasreportesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.quesada.com.canvasreportesapp.Adapters.AlumnosAdapter;
import app.quesada.com.canvasreportesapp.Singleton.ApiServiceGenerator;
import app.quesada.com.canvasreportesapp.interfaces.ApiService;
import app.quesada.com.canvasreportesapp.models.Alumno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumnosActivity extends AppCompatActivity {

    private static final String TAG = AlumnosActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private Integer course_id;
    private RecyclerView alumnosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        //Esto elimina el toolbar de la app
        //getSupportActionBar().hide();

        alumnosList = findViewById(R.id.recyclerview);
        alumnosList.setLayoutManager(new LinearLayoutManager(this));

        alumnosList.setAdapter(new AlumnosAdapter());

        course_id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + course_id);

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Alumno>> call = service.getAlumnos(course_id);

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Alumno> alumnos = response.body();
                        Log.d(TAG, "alumnos: " + alumnos);

                        AlumnosAdapter adapter = (AlumnosAdapter) alumnosList.getAdapter();
                        adapter.setAlumnos(alumnos);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(AlumnosActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(AlumnosActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    /**
     * 3ra ShowRegister
     * public void showRegister(View view){
     startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
     }
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }

}

