package titulacion.sise.canchala10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import titulacion.sise.canchala10.entidades.Campo;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.entidades.Sede;
import titulacion.sise.canchala10.fragments.CampoFragment;
import titulacion.sise.canchala10.fragments.MisReservasFragment;
import titulacion.sise.canchala10.fragments.ResumenFragment;
import titulacion.sise.canchala10.fragments.SedeFragment;
import titulacion.sise.canchala10.interfaces.IComunicaFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SedeFragment.OnFragmentInteractionListener, CampoFragment.OnFragmentInteractionListener,
        MisReservasFragment.OnFragmentInteractionListener, IComunicaFragment {

    SedeFragment sedeFragment;
    CampoFragment campoFragment;
    MisReservasFragment misReservasFragment;


    TextView tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sedeFragment = new SedeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, sedeFragment)
                .commit();

        View headerView = navigationView.getHeaderView(0);
        tvCorreo = (TextView)headerView.findViewById(R.id.tvCorreoSesion);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        tvCorreo.setText(currentUser.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_sedes) {
            sedeFragment = new SedeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, sedeFragment)
                    .commit();
        } else if (id == R.id.nav_mis_reservas) {
            misReservasFragment = new MisReservasFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, misReservasFragment)
                    .commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarSede(Sede sede) {
        campoFragment = new CampoFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("sede", sede);
        campoFragment.setArguments(bundleEnvio);

        //Cargar el fragment en el activity
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, campoFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void enviarResumen(Campo campo, List<Horario> horarios) {
        Intent intent = new Intent(getApplicationContext(), ResumenActivity.class);
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("campo", campo);
        bundleEnvio.putSerializable("horarios",  (Serializable) horarios);
        intent.putExtras(bundleEnvio);
        startActivity(intent);
    }
}
