package app.quesada.com.canvasreportesapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.quesada.com.canvasreportesapp.R;
import app.quesada.com.canvasreportesapp.models.Alumno;

public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ViewHolder>{
    private List<Alumno> alumnos;

    public AlumnosAdapter(){
        this.alumnos = new ArrayList<>();
    }

    public void setAlumnos(List<Alumno> alumnos){
        this.alumnos= alumnos;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Alumno alumno = this.alumnos.get(position);

        viewHolder.correoText.setText( "ID: "+alumno.getCourse_id());
        viewHolder.nombreText.setText(alumno.getName());


        /*
         String url = ApiService.API_BASE_URL + "/solicitudes/images/" + curso.getCaptura();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);
         */

    }

    @Override
    public int getItemCount() {
        return this.alumnos.size();
    }
}
