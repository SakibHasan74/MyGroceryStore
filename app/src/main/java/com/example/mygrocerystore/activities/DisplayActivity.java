package com.example.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mygrocerystore.MainActivity;
import com.example.mygrocerystore.R;
import com.google.firebase.auth.FirebaseAuth;

public class DisplayActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    Button adminSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mFirebaseAuth=FirebaseAuth.getInstance();
        adminSignIn=findViewById(R.id.admin);
        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayActivity.this,AdminActivity.class);
                startActivity(intent);


            }
        });



    }
    public void login(View view) {
        startActivity(new Intent(DisplayActivity.this, LoginActivity.class));
    }

    public void registration(View view) {

        startActivity(new Intent(DisplayActivity.this, RegistrationActivity.class));
    }


}







//    public void login(View view) {
//        startActivity(new Intent(DisplayActivity.this, LoginActivity.class));
//    }
//
//    public void registration(View view) {
//
//        startActivity(new Intent(DisplaActivity.this, RegistrationActivity.class));
//    }