package app.quesada.com.canvasreportesapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.Toast;


import java.util.List;

import app.quesada.com.canvasreportesapp.Adapters.AlumnosAdapter;
import app.quesada.com.canvasreportesapp.Adapters.CursosAdapter;
import app.quesada.com.canvasreportesapp.Singleton.ApiServiceGenerator;
import app.quesada.com.canvasreportesapp.interfaces.ApiService;
import app.quesada.com.canvasreportesapp.models.Alumno;
import app.quesada.com.canvasreportesapp.models.Curso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView cursoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cursoList = findViewById(R.id.recyclerview);
        cursoList.setLayoutManager(new LinearLayoutManager(this));

        cursoList.setAdapter(new CursosAdapter());

        initialize();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initialize(){


        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Curso>> call = service.getCursos();

        call.enqueue(new Callback<List<Curso>>(){
            @Override
            public void onResponse(Call<List<Curso>>call, Response<List<Curso>> response) {
                try {
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code:" + statusCode);
                    if (response.isSuccessful()) {
                        List<Curso>courses = response.body();
                        Log.d(TAG, "courses:" + courses);
                        CursosAdapter adapter = (CursosAdapter) cursoList.getAdapter();
                        adapter.setCursos(courses);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }
                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: "+ t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }
            @Override
            public void onFailure(Call<List<Curso>>call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}


