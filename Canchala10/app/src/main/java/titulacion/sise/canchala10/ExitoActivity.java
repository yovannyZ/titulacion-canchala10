package titulacion.sise.canchala10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import titulacion.sise.canchala10.fragments.MisReservasFragment;

public class ExitoActivity extends AppCompatActivity {


    TextView tvCodReserva;
    Button btnMisReservas;
    Button btnMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exito);

        tvCodReserva = (TextView) findViewById(R.id.tvCodReserva);
        btnMisReservas = (Button) findViewById(R.id.btnMisReservas);
        btnMenuPrincipal = (Button) findViewById(R.id.btnIrMenuPrincipal);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String codigoReserva = (String) bundle.get("codigo");
            tvCodReserva.setText(codigoReserva);
        }

    }

    @Override
    public void onBackPressed() {
    }

    public void irMenuPrincipal(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void irMisReservas(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("call", "activity_exito");
        startActivity(intent);
        finish();
    }
}
