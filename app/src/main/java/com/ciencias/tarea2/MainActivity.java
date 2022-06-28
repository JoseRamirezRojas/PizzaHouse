package com.ciencias.tarea2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ciencias.tarea2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ThemedNumberPicker picker1,picker2,picker3,picker4,picker5,picker6;

    private NavigationView nvDrawer;

    private String USER_ID = "USER_ID";
    private String USER_ID_TOAST = "Error al obtener perfil";
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        // add fragments
        FragmentManager fragmentManager = getSupportFragmentManager();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.FirstFragment,
                R.id.SecondFragment, R.id.ThirdFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        picker1 = findViewById(R.id.numberpicker_1);
        picker1.setMaxValue(8);
        picker1.setMinValue(0);
        picker2 = findViewById(R.id.numberpicker_2);
        picker2.setMaxValue(10);
        picker2.setMinValue(0);
        picker3 = findViewById(R.id.numberpicker_3);
        picker3.setMaxValue(8);
        picker3.setMinValue(0);
        picker4 = findViewById(R.id.numberpicker_4);
        picker4.setMaxValue(10);
        picker4.setMinValue(0);
        picker5 = findViewById(R.id.numberpicker_5);
        picker5.setMaxValue(8);
        picker5.setMinValue(0);
        picker6 = findViewById(R.id.numberpicker_6);
        picker6.setMaxValue(10);
        picker6.setMinValue(0);

        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker1.getValue();
                Log.d("picker value", Integer.toString(valuePicker1) );
            }
        });

        nvDrawer = (NavigationView) findViewById(R.id.nav_view);

        // Setup drawer view

        setupDrawerContent(nvDrawer);

        getUser();
    }

    private void getUser(){
        Intent intent = getIntent();
        this.userId = intent.getLongExtra(USER_ID, -1);
        if(this.userId == -1){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    USER_ID_TOAST, Toast.LENGTH_SHORT);
            toastName.show();
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_about_fragment:
                fragmentClass = AboutFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();

        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace
                ( R.id.nav_host_fragment_content_main, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        // mDrawer.closeDrawers();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(
                this,R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                Intent profileView = new Intent(MainActivity.this, ProfileActivity.class);
                profileView.putExtra(USER_ID, this.userId);
                startActivity(profileView);
                break;
            case R.id.action_information :
                Uri uri = Uri.parse("https://github.com/JoseRamirezRojas"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            case R.id.action_close :
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}