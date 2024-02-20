package com.example.niki;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null){
            Intent intent=new Intent(MainActivity.this,splash.class);
            startActivity(intent);
        }


    }
}