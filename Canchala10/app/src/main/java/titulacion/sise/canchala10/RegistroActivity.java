package titulacion.sise.canchala10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.Remote.Data.UsuarioPostResponse;
import titulacion.sise.canchala10.Remote.Data.UsuarioResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.entidades.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private TextView tvNombres;
    private TextView tvEmail;
    private TextView tvPassword;
    private FirebaseAuth mAuth;
    private SOService soService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        soService = ApiUtils.getSOService();
        inicializeControl();
    }

    private void inicializeControl(){
        tvNombres = (TextView)findViewById(R.id.tvNombres);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvPassword = (TextView)findViewById(R.id.tvPassword);
    }

    public void registrar(View view){
        //Obtener datos
        final String nombres = tvNombres.getText().toString();
        final String email = tvEmail.getText().toString();
        final String password = tvPassword.getText().toString();

        //creamos el usuario en firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Usuario usuario = new Usuario();
                            usuario.setClave(password);
                            usuario.setCorreo(email);
                            usuario.setNombres(nombres);
                            usuario.setIdTipoUsuario("1");

                            //creamos el usuario en nuestra bd
                            soService.addUsuario(usuario).enqueue(new Callback<UsuarioPostResponse>() {
                                @Override
                                public void onResponse(Call<UsuarioPostResponse> call, Response<UsuarioPostResponse> response) {
                                    if(response.isSuccessful()){
                                        UsuarioPostResponse usuarioResponse = response.body();

                                        if(usuarioResponse.getStatus()){
                                            Toast.makeText(getApplicationContext(), "Usuario creado correctamente",
                                                    Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<UsuarioPostResponse> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(getApplicationContext(), "Email o Password incorrecto",
                                    Toast.LENGTH_LONG).show();
                        } else if (e instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(getApplicationContext(), "Email incorrecto",
                                    Toast.LENGTH_LONG).show();
                        } else if (e instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "El email ya existe, favor de ingresar otro email",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}
