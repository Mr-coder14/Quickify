package LogInactiviies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.MainActivity;
import com.example.quiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView registerbtn;
    private Button loginbtn;
    private FirebaseAuth auth;
    private EditText email,password;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerbtn=findViewById(R.id.registerpage);
        loginbtn=findViewById(R.id.loginbtn);
        email=findViewById(R.id.emaillogin);
        password=findViewById(R.id.passwordlogin);
        auth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void login() {
        String email1 = email.getText().toString();
        String pass1 = password.getText().toString();
        if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(pass1)) {
            Toast.makeText(LoginActivity.this, "Enter All details", Toast.LENGTH_SHORT).show();
        } else if (pass1.length() < 6) {
            Toast.makeText(LoginActivity.this, "Incorrect Password ", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Logging in...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            auth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        progressDialog.dismiss();
                        finish();

                    } else {

                    Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
                }
            });
        }
    }
}