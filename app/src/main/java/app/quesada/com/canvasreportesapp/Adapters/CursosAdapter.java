package app.quesada.com.canvasreportesapp.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.quesada.com.canvasreportesapp.AlumnosActivity;
import app.quesada.com.canvasreportesapp.R;
import app.quesada.com.canvasreportesapp.interfaces.ApiService;
import app.quesada.com.canvasreportesapp.models.Curso;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.ViewHolder>{

    private static final String TAG = CursosAdapter.class.getSimpleName();

    private List<Curso> cursos;

    public CursosAdapter(){
        this.cursos = new ArrayList<>();
    }

    public void setCursos(List<Curso> cursos){
        this.cursos = cursos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView nombreText;
        public TextView correoText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            nombreText = itemView.findViewById(R.id.nombre_text);
            correoText = itemView.findViewById(R.id.correo_text);
        }
    }

    @Override
    public CursosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CursosAdapter.ViewHolder viewHolder, final int position) {

        final Curso curso = this.cursos.get(position);

        viewHolder.correoText.setText( "ID: "+curso.getId());
        viewHolder.nombreText.setText(curso.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), AlumnosActivity.class);
                intent.putExtra("ID", curso.getId());
                activity.startActivity(intent);
            }
        });

        /*
         String url = ApiService.API_BASE_URL + "/solicitudes/images/" + curso.getCaptura();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);
         */

    }

    @Override
    public int getItemCount() {
        return this.cursos.size();
    }
}

