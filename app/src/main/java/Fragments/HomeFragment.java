package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.quiz.MessageListActivity;
import com.example.quiz.R;
import com.google.firebase.auth.FirebaseAuth;

import LogInactiviies.LoginActivity;


public class HomeFragment extends Fragment {
    private LinearLayout cardView;
    private ImageView ms;

    public HomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view=LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragment,container,false);
       cardView=view.findViewById(R.id.tyu);
       ms=view.findViewById(R.id.messageactivity);
       cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       ms.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getContext(), MessageListActivity.class));
           }
       });

        return view;
    }
}