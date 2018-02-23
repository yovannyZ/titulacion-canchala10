package titulacion.sise.canchala10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.Remote.Data.HorarioResponse;
import titulacion.sise.canchala10.Remote.Data.TarifaResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.adaptadores.AdaptadorHorarios;
import titulacion.sise.canchala10.entidades.Campo;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.entidades.Tarifa;

public class ResumenActivity extends AppCompatActivity {

    TextView tvSede;
    TextView tvDireccion;
    TextView tvCampo;
    ListView lvHorarios;
    TextView tvTotalPagar;
    TextView tvHorarios;
    TextView tvExitoReserva;
    SOService soService;
    List<Tarifa> tarifas;
    List<Tarifa> tarifasSeleccionadas;
    int REQUEST_CODE = 1;

    Campo campo;
    List<Horario> horarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        tvSede = (TextView)findViewById(R.id.tvSede);
        tvDireccion = (TextView)findViewById(R.id.tvDireccion);
        tvCampo = (TextView)findViewById(R.id.tvCampo);
        lvHorarios = (ListView)findViewById(R.id.lvHorarios);
        tvTotalPagar = (TextView)findViewById(R.id.tvTotalPagar);
        tvHorarios = (TextView)findViewById(R.id.tvHorario);
        soService = ApiUtils.getSOService();
        tvExitoReserva = (TextView)findViewById(R.id.tvReservaExitosa);

        Bundle bundle = getIntent().getExtras();
        campo = (Campo) bundle.getSerializable("campo");
        horarios = (List<Horario>) bundle.getSerializable("horarios");
        String tHorarios = "";
        tvSede.setText("Sede: " + campo.getSede().getDescripcion());
        tvDireccion.setText("Dirección: " + campo.getSede().getDireccion());
        tvCampo.setText("Campo: " + campo.getDescripcion() + " " + campo.getCantidadJugadores() );
        //tvTotalPagar.setText(horarios.get(0).getId());

        getTarifas(campo.getId(),horarios );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                tvExitoReserva.setText("Reserva éxitosa");
            }
        }
    }

    private void getTarifas(String idCampo, final List<Horario> horarios){
        final List<String> lista = new ArrayList<String>();
        final BigDecimal[] totalPagar = {new BigDecimal("0")};
        final BigDecimal[] precio = new BigDecimal[1];
        final DecimalFormat formatter = new DecimalFormat("#,###.00");

        soService.getTarifasByCampo(idCampo).enqueue(new Callback<TarifaResponse>() {
            @Override
            public void onResponse(Call<TarifaResponse> call, Response<TarifaResponse> response) {
                if(response.isSuccessful()){
                    TarifaResponse tarifaResponse = response.body();

                    if(tarifaResponse.getStatus()){
                        tarifas = tarifaResponse.getTarifas();
                        tarifasSeleccionadas = new ArrayList<Tarifa>();
                        for(int i = 0; i< horarios.size(); i++){

                           for(int j = 0; j < tarifas.size(); j++){

                               String a = "";
                               String b = "";

                               a = tarifas.get(j).getIdHorario();
                               b = horarios.get(i).getId();

                               if(a.equals(b)){
                                   tarifasSeleccionadas.add(tarifas.get(j));
                                   lista.add("      Hora: " + horarios.get(i).getHoraInicio() + " - " + horarios.get(i).getHoraFin() + "  Precio: S/." +  formatter.format(new BigDecimal(tarifas.get(j).getPrecio())));
                                   totalPagar[0] = totalPagar[0].add(new BigDecimal(tarifas.get(j).getPrecio()));
                                   break;
                               }
                           }

                        }
                        //tvHorarios.setText("Horarios: " + tHorarios.substring(1,tHorarios.length()));
                        ArrayAdapter<String> adaptador;
                        adaptador = new ArrayAdapter<String>(getApplicationContext(),R.layout.text_view_horario,lista);
                        lvHorarios.setAdapter(adaptador);
                        tvTotalPagar.setText("total a pagar: S/." + formatter.format(totalPagar[0]));
                    }
                }
            }

            @Override
            public void onFailure(Call<TarifaResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void confirmarReserva(View view){
        Intent intent = new Intent(getApplicationContext(), CobroActivity.class);
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("campo", campo);
        bundleEnvio.putSerializable("tarifas",  (Serializable) tarifasSeleccionadas);
        intent.putExtras(bundleEnvio);
        startActivityForResult(intent, REQUEST_CODE);
    }

}
