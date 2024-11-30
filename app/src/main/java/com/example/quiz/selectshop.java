package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class selectshop extends AppCompatActivity {
    private ConstraintLayout maha,raj,prsanth,vasu;
    private ImageView ma,ra,pra,va;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectshop);
        maha=findViewById(R.id.mahalakshimi);
        raj=findViewById(R.id.rajkumar);
        prsanth=findViewById(R.id.prasanth);
        vasu=findViewById(R.id.vasu);
        ma=findViewById(R.id.mahatick);
        ra=findViewById(R.id.rajtick);
        pra=findViewById(R.id.prasanthtick);
        va=findViewById(R.id.vasutick);
        ma.setVisibility(View.GONE);
        ra.setVisibility(View.GONE);
        btn=findViewById(R.id.submitbtn);
        pra.setVisibility(View.GONE);
        va.setVisibility(View.GONE);
        maha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelection(ma);
                raj.setVisibility(View.GONE);
                prsanth.setVisibility(View.GONE);
                vasu.setVisibility(View.GONE);
            }
        });
        raj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelection(ra);
                maha.setVisibility(View.GONE);
                prsanth.setVisibility(View.GONE);
                vasu.setVisibility(View.GONE);
            }
        });
        prsanth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelection(pra);
                raj.setVisibility(View.GONE);
                maha.setVisibility(View.GONE);
                vasu.setVisibility(View.GONE);
            }
        });
        vasu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelection(va);
                raj.setVisibility(View.GONE);
                prsanth.setVisibility(View.GONE);
                maha.setVisibility(View.GONE);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectshop.this,sucessanimation.class));
                finish();
            }
        });
    }
    private void handleSelection(ImageView selectedTick) {
        ma.setVisibility(View.GONE);
        ra.setVisibility(View.GONE);
        pra.setVisibility(View.GONE);
        va.setVisibility(View.GONE);
        selectedTick.setVisibility(View.VISIBLE);

    }
}