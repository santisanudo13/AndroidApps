package es.unican.g3.tus.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import es.unican.g3.tus.R;


public class MainActivity extends AppCompatActivity implements DataCommunication {

    private int lineaIdentifier;
    private int paradaIdentifier;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParadasFragment fragmentParadas = new ParadasFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutElements, fragmentParadas);
        ft.commit();
        count++;
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }//onCreate


    @Override
    public int getLineaIdentifier() {
        return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        this.lineaIdentifier=identifier;
    }

    @Override
    public int getParadaIdentifier() { return paradaIdentifier; }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) { this.paradaIdentifier = paradaIdentifier; }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_portada:

                    ParadasFragment fragmentParadas = new ParadasFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft =  fm.beginTransaction();
                    ft.replace(R.id.frameLayoutElements, fragmentParadas);
                    ft.commit();

                    return true;
                case R.id.navigation_lineas:

                    LineasFragment fragmentLineas = new LineasFragment();
                    FragmentManager fm1 = getSupportFragmentManager();
                    FragmentTransaction ft1 =  fm1.beginTransaction();
                    ft1.replace(R.id.frameLayoutElements, fragmentLineas);
                    ft1.commit();

                    return true;
                case R.id.navigation_grupos:

                    GruposFragment fragmentGrupos = new GruposFragment();
                    FragmentManager fm2 = getSupportFragmentManager();
                    FragmentTransaction ft2 =  fm2.beginTransaction();
                    ft2.replace(R.id.frameLayoutElements, fragmentGrupos);
                    ft2.commit();

                    return true;

                default:
                    break;
            }
            return false;
        }

    };

}// MainActivity
