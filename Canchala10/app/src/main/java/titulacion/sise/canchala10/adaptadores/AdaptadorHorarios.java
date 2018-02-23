package titulacion.sise.canchala10.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.entidades.Horario;

/**
 * Created by yzeballos on 16/02/2018.
 */

public class AdaptadorHorarios extends
        RecyclerView.Adapter<AdaptadorHorarios.HorarioViewHolder> implements View.OnClickListener{

    public List<Horario> horarios;
    private View.OnClickListener listener;
    Context context;

    public AdaptadorHorarios( List<Horario> horarios){
        this.horarios = horarios;
    }

    @Override
    public HorarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horario_item, null, false);
        view.setOnClickListener(this);
        context = view.getContext();
        return new AdaptadorHorarios.HorarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorarioViewHolder holder, int position) {
        holder.tvHorario.setText(horarios.get(position).getHoraInicio() +" - "+horarios.get(position).getHoraFin() );
        holder.chkHorario.setTag(horarios.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return horarios.size();
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

    public class HorarioViewHolder extends RecyclerView.ViewHolder{

        CheckBox chkHorario;
        TextView tvHorario;

        public HorarioViewHolder(View itemView) {
            super(itemView);
            chkHorario = (CheckBox) itemView.findViewById(R.id.chkHorario);
            tvHorario = (TextView)itemView.findViewById(R.id.tvHora);
        }
    }
}
