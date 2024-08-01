// StatisticsActivity.java
package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    private ImageButton shomeb, scategoriesb, sstatisticsb, sbenefitsb, sprofileb;
    private int userId;
    private String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        shomeb = findViewById(R.id.shomeb);
        scategoriesb = findViewById(R.id.scategoriesb);
        sstatisticsb = findViewById(R.id.sstatisticsb);
        sbenefitsb = findViewById(R.id.sbenefitsb);
        sprofileb = findViewById(R.id.sprofileb);

        shomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        scategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
    }
}