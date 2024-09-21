package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import LogInactiviies.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button lgout;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        userid=auth.getCurrentUser().getUid();
        lgout=findViewById(R.id.lgout);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid!=null){
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });


    }
}