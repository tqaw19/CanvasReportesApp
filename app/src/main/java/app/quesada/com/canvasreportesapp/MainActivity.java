package app.quesada.com.canvasreportesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import app.quesada.com.canvasreportesapp.Adapters.CursosAdapter;
import app.quesada.com.canvasreportesapp.Singleton.ApiServiceGenerator;
import app.quesada.com.canvasreportesapp.interfaces.ApiService;
import app.quesada.com.canvasreportesapp.models.Curso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView cursosList;

    private static final int REGISTER_FORM_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esto elimina el toolbar de la app
        getSupportActionBar().hide();

        cursosList = findViewById(R.id.recyclerview);
        cursosList.setLayoutManager(new LinearLayoutManager(this));

        cursosList.setAdapter(new CursosAdapter());

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Curso>> call = service.getCursos();

        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Curso> cursos = response.body();
                        Log.d(TAG, "cursos: " + cursos);

                        CursosAdapter adapter = (CursosAdapter) cursosList.getAdapter();
                        adapter.setCursos(cursos);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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

