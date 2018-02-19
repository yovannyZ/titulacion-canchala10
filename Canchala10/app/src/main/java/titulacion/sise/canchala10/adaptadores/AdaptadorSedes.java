package titulacion.sise.canchala10.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.CircleTransform;
import titulacion.sise.canchala10.entidades.Sede;

/**
 * Created by yovanny on 14/02/2018.
 */

public class AdaptadorSedes extends
        RecyclerView.Adapter<AdaptadorSedes.SedesViewHolder> implements View.OnClickListener {

    List<Sede> Sedes;
    private View.OnClickListener listener;
    Context context;

    public AdaptadorSedes( List<Sede> Sedes){
        this.Sedes = Sedes;
    }

    @Override
    public SedesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sede_item, null, false);
        view.setOnClickListener(this);
        context = view.getContext();
        return new SedesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SedesViewHolder holder, int position) {

        holder.tvDireccion.setText(Sedes.get(position).getDireccion());
        holder.tvDescripcion.setText(Sedes.get(position).getDescripcion());
        Picasso.with(context).load(ApiUtils.imgUrl + Sedes.get(position).getUrlImagen()).transform(new CircleTransform(100,10)).into(holder.ivImagen);
        //holder.ivImagen.setText(Sedes.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return Sedes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class SedesViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescripcion;
        TextView tvDireccion;
        ImageView ivImagen;

        public SedesViewHolder(View itemView){
            super(itemView);
            tvDescripcion = (TextView)itemView.findViewById(R.id.idDescripcion);
            tvDireccion = (TextView)itemView.findViewById(R.id.idDireccion);
            ivImagen = (ImageView)itemView.findViewById(R.id.idImagen);
        }
    }

}
