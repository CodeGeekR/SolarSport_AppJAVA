package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    ImageButton chomeb, ccategoriesb, cstatisticsb, cbenefitsb, cprofileb;
    private int userId;
    private String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        chomeb = findViewById(R.id.chomeb);
        ccategoriesb = findViewById(R.id.ccategoriesb);
        cstatisticsb = findViewById(R.id.cstatisticsb);
        cbenefitsb = findViewById(R.id.cbenefitsb);
        cprofileb = findViewById(R.id.cprofileb);

        chomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        ccategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        cstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        cbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        cprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
    }
}