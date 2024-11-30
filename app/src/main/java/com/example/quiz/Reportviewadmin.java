package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import Classes.Reportissue;
public class Reportviewadmin extends AppCompatActivity {
    private TextView brandname,modelno,description,address,vehino;
    private ImageView imageView;
    private Reportissue reportissue;
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportviewadmin);
        brandname=findViewById(R.id.BrandNameadmin);
        address=findViewById(R.id.addres1);
        vehino=findViewById(R.id.vehino);
        btn=findViewById(R.id.backButtonproductview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        modelno=findViewById(R.id.modelnoadmin);
        imageView=findViewById(R.id.productImagevieww);
        description=findViewById(R.id.discriptionview);

        reportissue = getIntent().getParcelableExtra("report");

        if(reportissue!=null){
            brandname.setText(reportissue.getBrandname());
            modelno.setText(reportissue.getModelno());
            description.setText(reportissue.getDescription());
            vehino.setText("TN2235");
            address.setText("Paavai Engineering College,Pachal, Namakkal");

            if(reportissue.getImageuri()!=null){
                Uri imageUri = Uri.parse(reportissue.getImageuri());
                Glide.with(this)
                        .load(imageUri)
                        .placeholder(R.drawable.bikelogo)
                        .error(R.drawable.bikelogo)
                        .into(imageView);
            }else {
                imageView.setImageResource(R.drawable.bikelogo);
            }

        }
    }
}