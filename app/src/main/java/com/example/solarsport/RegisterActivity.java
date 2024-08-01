// RegisterActivity.java
package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private ImageButton rhomeb, rcategoriesb, rstatisticsb, rbenefitsb, rprofileb;
    private Button btnNext;
    private int userId;
    private String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        btnNext = findViewById(R.id.btnNext);
        rhomeb = findViewById(R.id.rhomeb);
        rcategoriesb = findViewById(R.id.rcategoriesb);
        rstatisticsb = findViewById(R.id.rstatisticsb);
        rbenefitsb = findViewById(R.id.rbenefitsb);
        rprofileb = findViewById(R.id.rprofileb);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        rhomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        rcategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        rstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        rbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        rprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
    }
}