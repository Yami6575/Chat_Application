package com.example.niki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.annotations.NonNull;


public class Login extends AppCompatActivity {
    Button button;
    TextView logsignup;
    EditText email,password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.logbutton);
        email=findViewById(R.id.editlogemail);
        password=findViewById(R.id.editlogpassword);
        logsignup=findViewById(R.id.loginsignup);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String Email = email.getText().toString();
                String pass = password.getText().toString();

                if ((TextUtils.isEmpty(Email))){

                    Toast.makeText(Login.this, "Enter The Email", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(pass)){

                    Toast.makeText(Login.this, "Enter The Password", Toast.LENGTH_SHORT).show();
                }else if (!Email.matches(emailPattern)){

                    email.setError("Give Proper Email Address");
                }else if (password.length()<6){

                    password.setError("More Then Six Characters");
                    Toast.makeText(Login.this, "Password Needs To Be Longer Then Six Characters", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                try {
                                     Intent intent = new Intent(Login.this , MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }

        });

    }

}