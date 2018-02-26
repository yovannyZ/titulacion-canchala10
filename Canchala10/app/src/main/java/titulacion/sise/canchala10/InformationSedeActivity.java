package titulacion.sise.canchala10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    private void getParameters(){
        Bundle bundle = getIntent().getExtras();
        sede = (Sede) bundle.getSerializable("sede");
    }

    private void setValues(){
        tvDescripcion.setText(sede.getDescripcion());
        tvDireccion.setText(sede.getDireccion());
        Picasso.with(getApplicationContext()).load(R.drawable.logo_1).transform(new CircleTransform(100,10)).into(ivSede);
    }
}
