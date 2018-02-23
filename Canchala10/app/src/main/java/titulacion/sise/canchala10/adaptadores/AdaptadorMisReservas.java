package titulacion.sise.canchala10.adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.CircleTransform;
import titulacion.sise.canchala10.entidades.Items;
import titulacion.sise.canchala10.entidades.Reserva;
import titulacion.sise.canchala10.entidades.Sede;

/**
 * Created by yzeballos on 23/02/2018.
 */

public class AdaptadorMisReservas extends
        RecyclerView.Adapter<AdaptadorMisReservas.MisReservasViewHolder> {

    List<Reserva> reservas;
    Context context;
    final DecimalFormat formatter = new DecimalFormat("#,###.00");

    public AdaptadorMisReservas( List<Reserva> reservas){
        this.reservas = reservas;
    }

    @Override
    public MisReservasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mis_resevas_item, null, false);
        context = view.getContext();
        return new MisReservasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MisReservasViewHolder holder, int position) {
        BigDecimal totalPagar = new BigDecimal("0");
        BigDecimal precio = new BigDecimal("0");
        List<String> lista = new ArrayList<String>();
        Formatter fmt = new Formatter();
        fmt.format("%08d",(Integer.parseInt(reservas.get(position).getId())));

        holder.tvDireccion.setText(reservas.get(position).getDetalles().get(0).getDireccion());
        holder.tvSede.setText(reservas.get(position).getDetalles().get(0).getSedeDescripcion());
        holder.tvCampo.setText(reservas.get(position).getDetalles().get(0).getDescripcion());
        holder.tvFecha.setText(reservas.get(position).getFecha());
        holder.tvCodReserva.setText("N° Reserva: " + fmt.toString());
        Picasso.with(context).load(ApiUtils.imgUrl + reservas.get(position).getDetalles().get(0).getUrlImagen()).transform(new CircleTransform(100,10)).into(holder.ivImagen);

        for (Items item: reservas.get(position).getDetalles() ) {
            lista.add("      Hora: " + item.getHoraInicio() + " - " + item.getHoraFin() + "  Precio: S/." +  formatter.format(new BigDecimal(item.getPrecio())));
            totalPagar = totalPagar.add(new BigDecimal(item.getPrecio()));
        }

        holder.tvtotalPagar.setText("Total a pagado: S/." + formatter.format(totalPagar));

        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(context.getApplicationContext(),R.layout.text_view_horario,lista);
        holder.lvHorarios.setAdapter(adaptador);
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public class MisReservasViewHolder extends RecyclerView.ViewHolder {

        TextView tvSede;
        TextView tvDireccion;
        ImageView ivImagen;
        TextView tvFecha;
        TextView tvtotalPagar;
        TextView tvCampo;
        ListView lvHorarios;
        TextView tvCodReserva;


        public MisReservasViewHolder(View itemView){
            super(itemView);
            tvSede = (TextView)itemView.findViewById(R.id.tvSede);
            tvDireccion = (TextView)itemView.findViewById(R.id.tvDireccion);
            ivImagen = (ImageView)itemView.findViewById(R.id.tvImagen);
            tvFecha = (TextView)itemView.findViewById(R.id.tvFecha);
            tvtotalPagar = (TextView)itemView.findViewById(R.id.tvTotalPagar);
            tvCampo = (TextView)itemView.findViewById(R.id.tvCampo);
            tvCodReserva = (TextView)itemView.findViewById(R.id.tvCodigoReserva);
            lvHorarios = (ListView)itemView.findViewById(R.id.lvHorarios);

        }
    }
}
