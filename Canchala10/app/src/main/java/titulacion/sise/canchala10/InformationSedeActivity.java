package titulacion.sise.canchala10;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.CircleTransform;
import titulacion.sise.canchala10.entidades.Sede;

public class InformationSedeActivity extends AppCompatActivity {

    ImageView ivSede;
    TextView tvDescripcion;
    TextView tvDireccion;
    TextView tvHoraAtencion;
    TextView tvTelefono;

    private Sede sede;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_sede);
        InicializeControls();
        getParameters();
        setValues();

    }

    private void InicializeControls(){
        ivSede = (ImageView) findViewById(R.id.ivSede);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        tvHoraAtencion = (TextView) findViewById(R.id.tvHoraAtencion);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);

    }

    private void getParameters(){
        Bundle bundle = getIntent().getExtras();
        sede = (Sede) bundle.getSerializable("sede");
    }

    private void setValues(){
        tvDescripcion.setText(sede.getDescripcion());
        tvDireccion.setText(sede.getDireccion());
        Picasso.with(getApplicationContext()).load(R.drawable.logo_1).transform(new CircleTransform(100,10)).into(ivSede);
        tvHoraAtencion.setText(sede.getHora_atencion());
        tvTelefono.setText(sede.getTelefono());

    }

    public void llamarTelefono(View view){
        Toast.makeText(this, "funciona", Toast.LENGTH_SHORT).show();
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Llamada");
        builder.setMessage("¿LLamar al " + sede.getTelefono() + "?");
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sede.getTelefono()));
                        if(ActivityCompat.checkSelfPermission(InformationSedeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            return;
                        }

                        startActivity(intent);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
/*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Exit..!!!");

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to exit");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(InformationSedeActivity.this,"You clicked over No",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
    }
}
