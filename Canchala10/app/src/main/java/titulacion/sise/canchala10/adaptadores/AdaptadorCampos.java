package titulacion.sise.canchala10.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.Remote.Data.HorarioResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.Global;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.CircleTransform;
import titulacion.sise.canchala10.entidades.Campo;
import titulacion.sise.canchala10.interfaces.IComunicaFragment;

/**
 * Created by yzeballos on 16/02/2018.
 */


public class AdaptadorCampos extends
        RecyclerView.Adapter<AdaptadorCampos.CampoViewHolder> implements View.OnClickListener{

    List<Campo> campos;
    List<Horario> todosHorarios;
    private View.OnClickListener listener;
    Context context;
    int  pos   ;
    AdaptadorHorarios adaptadorHorarios;
    private GridLayoutManager glm;
    SOService soService;
    List<Horario> horarios;

    public AdaptadorCampos( List<Campo> campos){
        this.campos = campos;

    }

    public void ObtenerHorarios(){
        soService.getHorarios().enqueue(new Callback<HorarioResponse>() {
            @Override
            public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                if(response.isSuccessful()){
                    HorarioResponse horarioResponse = response.body();
                    if(horarioResponse.getStatus()){
                        todosHorarios = horarioResponse.getHorarios();
                    }
                }
            }

            @Override
            public void onFailure(Call<HorarioResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public CampoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.campo_item, null, false);
        view.setOnClickListener(this);
        context = view.getContext();
        return new CampoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampoViewHolder holder, int position) {
        pos = position;
        holder.tvDescripcion.setText(campos.get(position).getDescripcion());
        Picasso.with(context).load(ApiUtils.imgUrl + campos.get(position).getImagen()).transform(new CircleTransform(100,10)).into(holder.ivImagen);
        glm = new GridLayoutManager(context, 2);
        holder.recyclerHorarios.setLayoutManager(glm);
        holder.recyclerHorarios.setLayoutManager(new LinearLayoutManager(context));
        soService = ApiUtils.getSOService();
        CargarHorarios(holder, campos.get(position).getId());
        ObtenerHorarios();
    }

    public void CargarHorarios(final CampoViewHolder holder, String idCampo){
        String fecha = ((Global) context.getApplicationContext()).getFechaReserva();
        soService.getHorariosByCampoFecha(idCampo,fecha).enqueue(new Callback<HorarioResponse>() {
            @Override
            public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                if(response.isSuccessful()){
                    HorarioResponse horarioResponse = response.body();
                    if(horarioResponse.getStatus()){
                        horarios = horarioResponse.getHorarios();
                        adaptadorHorarios = new AdaptadorHorarios(horarios);
                        glm = new GridLayoutManager(context, 2);
                        holder.recyclerHorarios.setLayoutManager(glm);
                        holder.recyclerHorarios.setAdapter(adaptadorHorarios);
                    }
                }
            }

            @Override
            public void onFailure(Call<HorarioResponse> call, Throwable t) {
                Toast.makeText(context, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
}

    @Override
    public int getItemCount() {
        return campos.size();
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

    public class CampoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvDescripcion;
        ImageView ivImagen;
        RecyclerView recyclerHorarios;
        Button btnReservar;
        IComunicaFragment iComunicaFragment;
        Activity activity;



        public CampoViewHolder(View itemView) {
            super(itemView);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            ivImagen = (ImageView)itemView.findViewById(R.id.tvImagen);
            recyclerHorarios = (RecyclerView) itemView.findViewById(R.id.recyclerHorarios);
            btnReservar = (Button)itemView.findViewById(R.id.btnReserva);
            btnReservar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            List<Horario> horaiosElegidos = new ArrayList<Horario>();
            if (view.getId() == btnReservar.getId()){
                int count = recyclerHorarios.getAdapter().getItemCount();
                List<Integer> data = new ArrayList<Integer>() ;
                for (int i = 0; i < count; i++){
                    ViewGroup row = (ViewGroup) recyclerHorarios.getChildAt(i);
                    CheckBox tvTest = (CheckBox) row.findViewById(R.id.chkHorario);
                    boolean v = tvTest.isChecked();

                    if(v){
                        Object obj = tvTest.getTag();
                        data.add(Integer.parseInt((String)obj));
                    }
                }

                for (int i = 0; i < data.size(); i++){
                    for (Horario item  : todosHorarios) {
                        String idHorario = ""+data.get(i);
                         if(item.getId().equals(idHorario)){
                            horaiosElegidos.add(item);
                            break;
                        }
                    }
                }

                this.activity =(Activity) view.getContext();
                iComunicaFragment =(IComunicaFragment) this.activity;
                iComunicaFragment.enviarResumen(campos.get(getAdapterPosition()), horaiosElegidos);
            }

        }
    }
}
