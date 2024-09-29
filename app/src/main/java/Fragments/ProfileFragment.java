package Fragments;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.quiz.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Adaptor.TabsAdapter;
import Classes.User;
import LogInactiviies.LoginActivity;

public class ProfileFragment extends Fragment {
    private TextView email,username;
    private DatabaseReference database;
    private String userid;
    private Button lgout;
    private FirebaseAuth auth;
    private User user;
    private FirebaseUser us;
    private TabLayout tableLayout;
    private ViewPager viewPager;

    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_profile,container,false);
        //email=view.findViewById(R.id.emailtxt);
        username=view.findViewById(R.id.usernametxt);
        lgout=view.findViewById(R.id.lgout);
        tableLayout=view.findViewById(R.id.tab_mains);
        viewPager=view.findViewById(R.id.viewpagermain);
        auth=FirebaseAuth.getInstance();
        us=auth.getCurrentUser();
        TabsAdapter tabsAdaptor=new TabsAdapter(getParentFragmentManager());
        viewPager.setAdapter(tabsAdaptor);
        tableLayout.setupWithViewPager(viewPager);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailogbox();
            }
        });
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        userid= FirebaseAuth.getInstance().getUid();
        database=FirebaseDatabase.getInstance().getReference().child("users").child(userid);
        userinformation();
        return view;
    }

    private void dailogbox() {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (us != null) {
                    auth.signOut();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void userinformation() {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(User.class);

                    if (user != null) {
                        //email.setText(user.getEmail());
                        username.setText(user.getName());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}