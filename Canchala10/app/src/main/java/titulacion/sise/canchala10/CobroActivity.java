package titulacion.sise.canchala10;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.Remote.Data.ReservaPostResponse;
import titulacion.sise.canchala10.Remote.Data.ReservaResponse;
import titulacion.sise.canchala10.Remote.Data.TarifaResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.entidades.Reserva;
import titulacion.sise.canchala10.entidades.ReservaDetalle;

public class CobroActivity extends AppCompatActivity {

    EditText tvNroTarjeta;
    EditText tvNombreCliente;
    EditText tvMes;
    EditText tvAnio;
    EditText tvCvv;
    SOService soService;

    List<EditText> campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro);
        Inicializecontrol();
        soService = ApiUtils.getSOService();
    }

    private void addReserva(){
        Reserva reserva = new Reserva();
        List<ReservaDetalle> items = new  ArrayList<ReservaDetalle>();
        reserva.setCodigo("003");
        reserva.setCorreo("yovanny.zeballos@resemin.com");
        reserva.setFecha("17/17/2018");

        ReservaDetalle item = new ReservaDetalle();
        item.setId_tarifa("1");
        item.setPrecio("30");
        items.add(item);
        reserva.setItems(items);

        soService.addReserva(reserva).enqueue(new Callback<ReservaPostResponse>() {
            @Override
            public void onResponse(Call<ReservaPostResponse> call, Response<ReservaPostResponse> response) {
                if(response.isSuccessful()){
                    ReservaPostResponse reservaPostResponse = response.body();
                    Toast.makeText(getApplicationContext(), String.valueOf(reservaPostResponse.getResponse()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReservaPostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Inicializecontrol(){
        tvNroTarjeta = (EditText)findViewById(R.id.tvNroTarjeta);
        tvNombreCliente = (EditText)findViewById(R.id.tvNombreCliente);
        tvMes = (EditText)findViewById(R.id.tvMes);
        tvAnio = (EditText)findViewById(R.id.tvAnio);
        tvCvv = (EditText)findViewById(R.id.tvCvv);
        campos = Arrays.asList(tvNroTarjeta,tvNombreCliente,tvMes,tvAnio,tvCvv);
    }

    private boolean verificarCampos(){
        boolean falta =  false;

        for(EditText text : campos){
            text.setHintTextColor(ContextCompat.getColor(this, R.color.accent));
        }

        for(EditText text : campos){
            if(text.getText().toString().trim().isEmpty()){
                text.setHintTextColor(ContextCompat.getColor(this, R.color.error));
                falta = true;
            }
        }


        return falta;
    }

    public void PagarTarjeta(View view){
        if(!verificarCampos()){
            addReserva();
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.faltancampos), Toast.LENGTH_SHORT).show();
        }
    }
}
