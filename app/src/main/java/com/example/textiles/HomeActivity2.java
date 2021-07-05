package com.example.textiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class HomeActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.profileIm);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeIm:
            {   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            }

            case R.id.profileIm:
            { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
            break;
            }

            case R.id.orderDetailIm:
            { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrderDetailsFragment()).commit();
                break;
            }

            case R.id.orderProcessingIm:
            { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrderProcessingFragment()).commit();
                break;
            }

            case R.id.deliveryIm:
            { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DeliveryFragment()).commit();
                break;
            }

            case R.id.paymentsIm:
            { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PaymentsFragment()).commit();
                break;
            }

            case R.id.paymentsHistoryIm:
            {getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PaymentsHistoryFragment()).commit();
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }
}