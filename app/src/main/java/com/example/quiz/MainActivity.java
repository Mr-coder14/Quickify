package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


import Fragments.BoardFragment;
import Fragments.BookFragment;
import Fragments.HomeFragment;
import Fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity {
    private ChipNavigationBar bottomNavigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomappbar);
        if (savedInstanceState == null) {
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            bottomNavigationView.setItemSelected(R.id.Homebottom,true);
        }

        bottomNavigationView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                int id=i;
                if(id==R.id.Homebottom){
                    fragment= new HomeFragment();
                }if(id==R.id.boardbottom){
                    fragment=new BoardFragment();
                }
                if (id==R.id.bookmarkbottom){
                    fragment=new BookFragment();
                }if(id==R.id.profilebottom){
                    fragment=new ProfileFragment();
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

                }
            }
        });
    }
}