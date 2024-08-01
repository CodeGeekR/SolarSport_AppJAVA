// BenefitsActivity.java
package com.example.solarsport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BenefitsActivity extends AppCompatActivity {

    private ImageButton bhomeb, bcategoriesb, bstatisticsb, bbenefitsb, bprofileb;
    private int userId;
    private String fullName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benefits_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        bhomeb = findViewById(R.id.bhomeb);
        bcategoriesb = findViewById(R.id.bcategoriesb);
        bstatisticsb = findViewById(R.id.bstatisticsb);
        bbenefitsb = findViewById(R.id.bbenefitsb);
        bprofileb = findViewById(R.id.bprofileb);

        bhomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitsActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        bcategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitsActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        bstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitsActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        bbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitsActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        bprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitsActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
    }
}