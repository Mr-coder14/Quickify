package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptor.reportadaptoradmin;
import Classes.Reportissue;


public class Homeadminfragment extends Fragment {
    private RecyclerView recyclerView;
    private Adaptor.reportadaptoradmin adaptor;
    private ArrayList<Reportissue> reportissues=new ArrayList<>();
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    public Homeadminfragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_homeadminfragment, container, false);
        recyclerView=view.findViewById(R.id.recycleadmin);
        progressBar=view.findViewById(R.id.progressBarhadmin);
        progressBar.setVisibility(View.VISIBLE);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("issues");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    reportissues.clear();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Reportissue reportissue = d.getValue(Reportissue.class);
                            if (reportissue != null) {

                                reportissues.add(reportissue);
                                Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    adaptor.notifyDataSetChanged();
                }progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor=new reportadaptoradmin(reportissues);
        recyclerView.setAdapter(adaptor);
        return view;
    }
}