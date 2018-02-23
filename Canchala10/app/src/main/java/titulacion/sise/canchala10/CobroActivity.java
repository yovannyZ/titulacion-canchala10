package titulacion.sise.canchala10;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.Remote.Data.PostResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.Global;
import titulacion.sise.canchala10.entidades.Campo;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.entidades.Reserva;
import titulacion.sise.canchala10.entidades.ReservaDetalle;
import titulacion.sise.canchala10.entidades.Tarifa;

public class CobroActivity extends AppCompatActivity {

    EditText tvNroTarjeta;
    EditText tvNombreCliente;
    EditText tvMes;
    EditText tvAnio;
    EditText tvCvv;
    SOService soService;
    FirebaseAuth mAuth;
    List<Tarifa> tarifas;
    List<EditText> campos;
    Button btnPagar;
    DecimalFormat formatter = new DecimalFormat("#,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro);
        Inicializecontrol();
        soService = ApiUtils.getSOService();
        mAuth = FirebaseAuth.getInstance();

        //obtenemos los parametros enviados desde ResumenActivity
        Bundle bundle = getIntent().getExtras();
        Campo campo = (Campo) bundle.getSerializable("campo");
        tarifas = (List<Tarifa>) bundle.getSerializable("tarifas");

        //Obtenemos total a pagar
        BigDecimal totalPagar = new BigDecimal("0");

        for(Tarifa tarifa : tarifas){
            totalPagar = totalPagar.add(new BigDecimal(tarifa.getPrecio()));
        }
        btnPagar = (Button) findViewById(R.id.btnPagar);
        btnPagar.setText("PAGAR: S/. " + formatter.format(totalPagar));
    }

    private void addReserva() throws Exception {
        String datString = ((Global) getApplication()).getFechaReserva();
        Reserva reserva = new Reserva();
        final List<ReservaDetalle> items = new  ArrayList<ReservaDetalle>();

        //Obtener usuario actual
        FirebaseUser currentUser = mAuth.getCurrentUser();
        reserva.setCorreo(currentUser.getEmail());
        reserva.setFecha(datString);



        for (Tarifa tarifa: tarifas) {
            ReservaDetalle item;
            item = new ReservaDetalle();
            item.setId_tarifa(tarifa.getId());
            item.setPrecio(tarifa.getPrecio());
            items.add(item);
        }

        reserva.setItems(items);

        soService.addReserva(reserva).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, final Response<PostResponse> response) {
                if(response.isSuccessful()){
                    final PostResponse reservaPostResponse = response.body();
                    boolean exito = false;
                    if(reservaPostResponse.getStatus() && reservaPostResponse.getResponse() > 0 ){
                        Formatter fmt = new Formatter();
                        fmt.format("%08d",reservaPostResponse.getResponse());
                        irPantallaExito(fmt.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
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
            try {
                addReserva();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.faltancampos), Toast.LENGTH_SHORT).show();
        }
    }

    private void irPantallaExito(String codigoReserva){
        Intent intent = new Intent(getApplicationContext(), ExitoActivity.class);
        intent.putExtra("codigo", codigoReserva);
        startActivity(intent);
    }


}
