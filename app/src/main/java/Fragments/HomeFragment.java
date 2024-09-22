package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quiz.R;
import com.google.firebase.auth.FirebaseAuth;

import LogInactiviies.LoginActivity;


public class HomeFragment extends Fragment {
    private Button lgout;
    private FirebaseAuth auth;

    private String userid;

    public HomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view=LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragment,container,false);
        lgout=view.findViewById(R.id.lgout);
        auth=FirebaseAuth.getInstance();
        userid=auth.getCurrentUser().getUid();
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid!=null){
                    auth.signOut();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}