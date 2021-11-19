package com.example.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mygrocerystore.R;

public class AdminHomeActivity extends AppCompatActivity {
    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        card1=(CardView) findViewById(R.id.crd1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this,AdminAddNewActivity.class);
                startActivity(intent);

            }
        });


    }
}
