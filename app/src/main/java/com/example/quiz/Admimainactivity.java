package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import Fragments.HomeFragment;
import Fragments.Homeadminfragment;
import Fragments.progilefragmentadmin;

public class Admimainactivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admimainactivity);

        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        if (savedInstanceState == null) {
            fragment = new Homeadminfragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.adminfragment, fragment)
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.Homebottomadmin);
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.Homebottomadmin){
                    fragment=new Homeadminfragment();


                }if(id==R.id.profilebottomadmin){
                    fragment=new progilefragmentadmin();
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.adminfragment,fragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}