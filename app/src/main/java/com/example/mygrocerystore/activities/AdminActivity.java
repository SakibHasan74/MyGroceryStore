package com.example.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mygrocerystore.MainActivity;
import com.example.mygrocerystore.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    EditText email, password;
    Button adminSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        email = findViewById(R.id.email_login_admin);
        password = findViewById(R.id.password_login_admin);
        adminSign=findViewById(R.id.admin_btn_sign);
        adminSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailto=email.getText().toString();
                String pass=password.getText().toString();
                if (emailto.equals("sakibaust74@gmail.com") && pass.equals("123456")){
                    Intent intent=new Intent(AdminActivity.this,AdminHomeActivity.class);
                    startActivity(intent);
                }
            }
        });






    }
}

