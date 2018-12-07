package app.quesada.com.canvasreportesapp.Adapters;

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
import app.quesada.com.canvasreportesapp.models.Enrollment;

public class EnrollmentsAdapter extends RecyclerView.Adapter<EnrollmentsAdapter.ViewHolder>{
    private List<Enrollment> enrollments;

    public EnrollmentsAdapter(){

        this.enrollments = new ArrayList<>();
    } 

    public void setEnrollments(List<Enrollment> enrollments){
        this.enrollments= enrollments;
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
    public EnrollmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enrollment, parent, false);
        return new EnrollmentsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EnrollmentsAdapter.ViewHolder viewHolder, int position) {

        Enrollment enrollment = this.enrollments.get(position);

        viewHolder.correoText.setText( "UserID: "+enrollment.getUser_id());
        viewHolder.nombreText.setText(enrollment.getGrades().getCurrent_score());


        /*
         String url = ApiService.API_BASE_URL + "/solicitudes/images/" + curso.getCaptura();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);
         */
    }

    @Override
    public int getItemCount() {
        return this.enrollments.size();
    }
}
