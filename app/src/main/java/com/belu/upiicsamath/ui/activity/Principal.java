package com.belu.upiicsamath.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.HeaderViewListAdapter;
import android.widget.TextView;

import com.belu.upiicsamath.ui.fragment.FragmentAviso;
import com.belu.upiicsamath.ui.fragment.FragmentCalendar;
import com.belu.upiicsamath.ui.fragment.FragmentHorario;
import com.belu.upiicsamath.ui.fragment.FragmentPagina;
import com.belu.upiicsamath.R;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nombre;
    private String apellidoP;
    private String boleta;
    private TextView tvNombre;
    private TextView tvBoleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
/*
        nombre = getIntent().getExtras().getString("Nombre");
        apellidoP = getIntent().getExtras().getString("Apellido");
        boleta = getIntent().getExtras().getString("Boleta");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_horario) {
            getFragmentManager().beginTransaction().replace(R.id.container_home, new FragmentHorario()).commit();
        } else if (id == R.id.nav_pagina) {
            getFragmentManager().beginTransaction().replace(R.id.container_home, new FragmentPagina()).commit();
        } else if (id == R.id.nav_calendario) {
            getFragmentManager().beginTransaction().replace(R.id.container_home, new FragmentCalendar()).commit();
        } else if (id == R.id.nav_aviso) {
            getFragmentManager().beginTransaction().replace(R.id.container_home, new FragmentAviso()).commit();
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(this,SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
