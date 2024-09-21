package LogInactiviies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;

public class LoginActivity extends AppCompatActivity {
    private TextView registerbtn;
    private Button loginbtn;
    private EditText email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerbtn=findViewById(R.id.registerpage);
        loginbtn=findViewById(R.id.loginbtn);
        email=findViewById(R.id.emaillogin);
        password=findViewById(R.id.passwordlogin);
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
        String em=email.getText().toString();
        String pas=password.getText().toString();

        if(TextUtils.isEmpty(em) || TextUtils.isEmpty(pas)){
            email.setError("Enter all details");
            Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show();

        }
    }
}